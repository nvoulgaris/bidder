package com.bluebanana.bidder.usecase;

import com.bluebanana.bidder.usecase.response.BidDto;
import com.bluebanana.bidder.usecase.response.BidResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CampaignConverter {

  public BidResponseDto toBidResponseDto(Campaign campaign, String id) {
    return new BidResponseDto.Builder().
        withId(id).
        withBid(new BidDto.Builder().
            withCampaignId(campaign.getId()).
            withPrice(campaign.getPrice()).
            withAdm(campaign.getAdm()).
            build()).
        build();
  }
}
