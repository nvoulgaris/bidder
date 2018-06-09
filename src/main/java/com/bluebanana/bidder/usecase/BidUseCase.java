package com.bluebanana.bidder.usecase;

import com.bluebanana.bidder.gateway.CampaignGateway;
import com.bluebanana.bidder.model.BidRequestDto;
import com.bluebanana.bidder.model.BidResponseDto;
import com.bluebanana.bidder.model.Campaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidUseCase implements UseCase<BidRequestDto, BidResponseDto> {

  @Autowired private CampaignGateway targetedCampaigns;

  @Override
  public Optional<BidResponseDto> execute(BidRequestDto bidRequestDto) {
    List<Campaign> availableCampaigns = targetedCampaigns.retrieve();
    if (availableCampaigns.isEmpty()) {
      return Optional.empty();
    }
    return null;
  }
}
