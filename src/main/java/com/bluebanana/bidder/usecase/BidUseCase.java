package com.bluebanana.bidder.usecase;

import com.bluebanana.bidder.gateway.CampaignGateway;
import com.bluebanana.bidder.model.Campaign;
import com.bluebanana.bidder.model.request.BidRequestDto;
import com.bluebanana.bidder.model.response.BidResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
public class BidUseCase implements UseCase<BidRequestDto, BidResponseDto> {

  @Autowired private CampaignGateway targetedCampaigns;
  @Autowired private CampaignConverter campaignConverter;

  @Override
  public Optional<BidResponseDto> execute(final BidRequestDto request) {
    Optional<Campaign> result = targetedCampaigns.retrieve().
        stream().
        filter(campaign -> campaign.isFor(request.country())).
        max(Comparator.comparing(Campaign::getPrice));

    return result.map(campaign -> campaignConverter.toBidResponseDto(campaign, request.getId()));
  }
}
