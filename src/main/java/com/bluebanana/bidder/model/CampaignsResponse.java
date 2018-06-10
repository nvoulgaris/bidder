package com.bluebanana.bidder.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CampaignsResponse {

  private final List<Campaign> campaigns;

  @JsonCreator
  public CampaignsResponse(@JsonProperty("campaigns") List<Campaign> campaigns) {
    this.campaigns = campaigns;
  }
}
