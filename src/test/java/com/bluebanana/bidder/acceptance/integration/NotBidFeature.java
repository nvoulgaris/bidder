package com.bluebanana.bidder.acceptance.integration;

import com.bluebanana.bidder.BidderApplication;
import com.bluebanana.bidder.JsonFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {BidderApplication.class})
public class NotBidFeature {

  private static final String BID_PATH = "/bid";
  private static final String NO_AVAILABLE_CAMPAIGNS_BID_REQUEST = "fixtures/bid/request/bid_for_no_available_campaign.json";
  private static final String NO_MATCHING_CAMPAIGNS_BID_REQUEST = "fixtures/bid/request/bid_for_no_matching_campaign.json";
  private static final String NO_AVAILABLE_CAMPAIGNS_RESPONSE = "fixtures/campaign/response/no_available_campaigns.json";
  private static final String NO_MATCHING_CAMPAIGNS_RESPONSE = "fixtures/campaign/response/no_matching_campaigns.json";
  private MockMvc mockMvc;
  private String noAvailableCampaignsBidRequest;
  private String noMatchingCampaignsBidRequest;
  private String noAvailableCampaigns;
  private String noMatchingCampaigns;

  @MockBean RestTemplate restTemplate;
  @Autowired WebApplicationContext webApplicationContext;

  private void prepareFixtures() {
    JsonFixtures jsonFixtures = new JsonFixtures();
    noAvailableCampaignsBidRequest = jsonFixtures.load(NO_AVAILABLE_CAMPAIGNS_BID_REQUEST);
    noMatchingCampaignsBidRequest = jsonFixtures.load(NO_MATCHING_CAMPAIGNS_BID_REQUEST);
    noAvailableCampaigns = jsonFixtures.load(NO_AVAILABLE_CAMPAIGNS_RESPONSE);
    noMatchingCampaigns = jsonFixtures.load(NO_MATCHING_CAMPAIGNS_RESPONSE);
  }

  private void mockNoAvailableCampaigns() {
    ResponseEntity<String> campaignsResponse = new ResponseEntity<>(noAvailableCampaigns, HttpStatus.OK);
    when(restTemplate.exchange(anyString(), any(), any(HttpEntity.class), any(Class.class))).thenReturn(campaignsResponse);
  }

  private void mockNoMatchingCampaigns() {
    ResponseEntity<String> campaignsResponse = new ResponseEntity<>(noMatchingCampaigns, HttpStatus.OK);
    when(restTemplate.exchange(anyString(), any(), any(HttpEntity.class), any(Class.class))).thenReturn(campaignsResponse);
  }

  @BeforeEach
  public void setUp() {
    mockMvc = webAppContextSetup(webApplicationContext).build();
    prepareFixtures();
  }

  @Test
  public void notBidWhenThereAreNoAvailableCampaigns() throws Exception {
    mockNoAvailableCampaigns();

    mockMvc.perform(post(BID_PATH)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(noAvailableCampaignsBidRequest))
        .andDo(print())
        .andExpect(status().isNoContent())
        .andReturn();
  }

  @Test
  public void notBidWhenThereAreNoMatchingCampaigns() throws Exception {
    mockNoMatchingCampaigns();

    mockMvc.perform(post(BID_PATH)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(noMatchingCampaignsBidRequest))
        .andDo(print())
        .andExpect(status().isNoContent())
        .andReturn();
  }
}
