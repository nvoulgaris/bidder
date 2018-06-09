package com.bluebanana.bidder.usecase;

import com.bluebanana.bidder.gateway.CampaignGateway;
import com.bluebanana.bidder.model.response.BidResponseDto;
import com.bluebanana.bidder.model.Campaign;
import com.bluebanana.bidder.model.request.BidRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class BidUseCase implements UseCase<BidRequestDto, BidResponseDto> {

  @Autowired private CampaignGateway targetedCampaigns;
  @Autowired private CampaignFilter campaignFilter;
  @Autowired CampaignConverter campaignConverter;

  @Override
  public Optional<BidResponseDto> execute(BidRequestDto request) {
    List<Campaign> availableCampaigns = targetedCampaigns.retrieve();

    List<Campaign> targetingCampaigns = campaignFilter.applyCriterion(availableCampaigns, request.country());

    if (targetingCampaigns.isEmpty()) {
      return Optional.empty();
    }

    Optional<Campaign> highestPaying = targetingCampaigns.stream().
        max(Comparator.comparing(Campaign::getPrice));

    BidResponseDto campaign = campaignConverter.toBidResponseDto(highestPaying.get(), request.getId());

    return Optional.of(campaign);
  }
}
