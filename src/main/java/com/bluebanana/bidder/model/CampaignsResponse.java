package com.bluebanana.bidder.model;

import java.util.List;

public class CampaignsResponse {

  private List<Campaign> campaigns;

  public CampaignsResponse() {
  }

  public CampaignsResponse(List<Campaign> campaigns) {
    this.campaigns = campaigns;
  }

  public List<Campaign> getCampaigns() {
    return campaigns;
  }

  public void setCampaigns(List<Campaign> campaigns) {
    this.campaigns = campaigns;
  }
}
