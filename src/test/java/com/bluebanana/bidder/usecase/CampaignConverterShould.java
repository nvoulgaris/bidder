package com.bluebanana.bidder.usecase;

import com.bluebanana.bidder.gateway.TargetedCampaign;
import com.bluebanana.bidder.usecase.response.Bid;
import com.bluebanana.bidder.usecase.response.BidResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class CampaignConverterShould {

  private CampaignConverter campaignConverter;

  @BeforeEach
  public void setUp() {
    campaignConverter = new CampaignConverter();
  }

  @Test
  public void convertCampaignToBidResponseDto() {
    String id = "e7fe51ce4f6376876353ff0961c2cb0d";
    Campaign campaign = new TargetedCampaign("5a3dce46", "name", 1.23, "testAdm", new ArrayList<>());
    BidResponseDto expectedBidResponseDto = new BidResponseDto.Builder().
        withId("e7fe51ce4f6376876353ff0961c2cb0d").
        withBid(new Bid.Builder().
            withCampaignId("5a3dce46").
            withPrice(1.23).
            withAdm("testAdm").
            build()).
        build();

    BidResponseDto actualBidResponseDto = campaignConverter.toBidResponseDto(campaign, id);

    assertThat(actualBidResponseDto).isEqualToComparingFieldByFieldRecursively(expectedBidResponseDto);
  }
}