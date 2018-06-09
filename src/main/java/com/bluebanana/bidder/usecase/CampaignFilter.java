package com.bluebanana.bidder.usecase;

import com.bluebanana.bidder.model.Campaign;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CampaignFilter {

  public List<Campaign> applyCriterion(List<Campaign> campaigns, String country) {
    return campaigns.stream().
        filter(campaign -> campaign.getTargetedCountries().contains(country)).
        collect(Collectors.toList());
  }
}
