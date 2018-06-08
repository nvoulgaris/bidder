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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {BidderApplication.class})
public class NotBidFeature {

  private static final String BIDS_PATH = "/bid";
  private static final String BID_REQUEST = "fixtures/requests/no_available_campaign.json";
  private MockMvc mockMvc;
  private JsonFixtures jsonFixtures;
  private String bidRequest;

  @Autowired WebApplicationContext webApplicationContext;

  private void prepareFixtures() {
    jsonFixtures = new JsonFixtures();
    bidRequest = jsonFixtures.load(BID_REQUEST);
  }

  @BeforeEach
  public void setUp() {
    mockMvc = webAppContextSetup(webApplicationContext).build();
    prepareFixtures();
  }

  @Test
  public void notBidWhenThereAreNoAvailableCampaigns() throws Exception {
    mockMvc.perform(post(BIDS_PATH)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(bidRequest))
        .andDo(print())
        .andExpect(status().isNoContent())
//        .andExpect(content().contentType(CONTENT_TYPE))
        .andReturn();
  }
}
