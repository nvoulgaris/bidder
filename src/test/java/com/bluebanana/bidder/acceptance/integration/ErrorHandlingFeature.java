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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {BidderApplication.class})
public class ErrorHandlingFeature {

  private static final String BID_PATH = "/bid";
  private static final String BID_REQUEST = "fixtures/bid/request/bid.json";
  private static final String CAMPAIGNS_RESPONSE = "fixtures/campaign/response/malformed_campaigns.json";

  private MockMvc mockMvc;
  private String bidRequest;
  private String campaigns;

  @MockBean RestTemplate restTemplate;
  @Autowired WebApplicationContext webApplicationContext;

  private void prepareFixtures() {
    JsonFixtures jsonFixtures = new JsonFixtures();
    bidRequest = jsonFixtures.load(BID_REQUEST);
    campaigns = jsonFixtures.load(CAMPAIGNS_RESPONSE);
  }

  private void mockMalformedCampaigns() {
    ResponseEntity<String> campaignsResponse = new ResponseEntity<>(campaigns, HttpStatus.OK);
    when(restTemplate.exchange(anyString(), any(), any(HttpEntity.class), any(Class.class))).thenReturn(campaignsResponse);
  }

  @BeforeEach
  public void setUp() {
    mockMvc = webAppContextSetup(webApplicationContext).build();
    prepareFixtures();
  }

  @Test
  public void respondWithInternalServerErrorStatusCodeWhenCannotParseCampaigns() throws Exception {
    mockMalformedCampaigns();

    mockMvc.perform(post(BID_PATH)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(bidRequest))
        .andDo(print())
        .andExpect(status().isInternalServerError())
        .andReturn();
  }
}
