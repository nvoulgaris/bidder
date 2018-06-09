package com.bluebanana.bidder.gateway;

import com.bluebanana.bidder.model.Campaign;
import com.bluebanana.bidder.model.exception.RetrieveCampaignsException;

import java.util.List;

public interface CampaignGateway {
  List<Campaign> retrieve() throws RetrieveCampaignsException;
}
