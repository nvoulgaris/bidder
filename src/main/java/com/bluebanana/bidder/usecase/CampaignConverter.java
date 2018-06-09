package com.bluebanana.bidder.usecase;

import com.bluebanana.bidder.model.Campaign;
import com.bluebanana.bidder.model.response.Bid;
import com.bluebanana.bidder.model.response.BidResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CampaignConverter {

  public BidResponseDto toBidResponseDto(Campaign campaign, String id) {
    return new BidResponseDto.Builder().
        withId(id).
        withBid(new Bid.Builder().
            withCampaignId(campaign.getId()).
            withPrice(campaign.getPrice()).
            withAdm(campaign.getAdm()).
            build()).
        build();
  }
}
