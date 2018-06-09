package com.bluebanana.bidder.acceptance.integration;

import com.bluebanana.bidder.BidderApplication;
import com.bluebanana.bidder.JsonFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@Disabled("Under implementation")
@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {BidderApplication.class})
public class BidFeature {

  private static final String BID_PATH = "/bid";
  private static final String BID_REQUEST = "fixtures/bid/request/bid.json";
  private static final String EXPECTED_BID_RESPONSE = "fixtures/bid/response/expected_bid.json";
  private static final String CAMPAIGNS_RESPONSE = "fixtures/campaign/response/available_campaigns.json";
  private MockMvc mockMvc;
  private String bidRequest;
  private String campaigns;

  @MockBean
  RestTemplate restTemplate;
  @Autowired
  WebApplicationContext webApplicationContext;

  private void prepareFixtures() {
    JsonFixtures jsonFixtures = new JsonFixtures();
    bidRequest = jsonFixtures.load(BID_REQUEST);
    campaigns = jsonFixtures.load(CAMPAIGNS_RESPONSE);
  }

  private void mockAvailableCampaigns() {
    ResponseEntity<String> campaignsResponse = new ResponseEntity<>(campaigns, HttpStatus.OK);
    when(restTemplate.exchange(anyString(), any(), any(HttpEntity.class), any(Class.class))).thenReturn(campaignsResponse);
  }
  
  @BeforeEach
  public void setUp() {
    mockMvc = webAppContextSetup(webApplicationContext).build();
    prepareFixtures();
  }

  @Test
  public void bidForTheHighestPayingCampaignThatMatchesTheTargetingCriteria() throws Exception {
    mockAvailableCampaigns();

    MvcResult result = mockMvc.perform(post(BID_PATH)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(bidRequest))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();

    String content = result.getResponse().getContentAsString();
    assertThat(content).isEqualTo(EXPECTED_BID_RESPONSE);
  }
}
