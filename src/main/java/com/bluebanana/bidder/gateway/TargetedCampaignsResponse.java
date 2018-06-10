package com.bluebanana.bidder.gateway;

import com.bluebanana.bidder.usecase.Campaign;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TargetedCampaignsResponse {

  private final List<Campaign> campaigns;

  @JsonCreator
  public TargetedCampaignsResponse(@JsonProperty("campaigns") List<Campaign> campaigns) {
    this.campaigns = campaigns;
  }
}
