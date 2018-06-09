package com.bluebanana.bidder.usecase;

import com.bluebanana.bidder.gateway.CampaignGateway;
import com.bluebanana.bidder.model.BidResponseDto;
import com.bluebanana.bidder.model.Campaign;
import com.bluebanana.bidder.model.request.BidRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidUseCase implements UseCase<BidRequestDto, BidResponseDto> {

  @Autowired private CampaignGateway targetedCampaigns;
  @Autowired private CampaignFilter campaignFilter;

  @Override
  public Optional<BidResponseDto> execute(BidRequestDto request) {
    List<Campaign> availableCampaigns = targetedCampaigns.retrieve();

    List<Campaign> targetingCampaigns = campaignFilter.applyCriterion(availableCampaigns, request.country());

    if (targetingCampaigns.isEmpty()) {
      return Optional.empty();
    }
    return null;
  }
}
