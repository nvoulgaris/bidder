package com.bluebanana.bidder.usecase;

import com.bluebanana.bidder.gateway.RetrieveCampaignsException;

import java.util.List;

public interface CampaignGateway {

  List<Campaign> retrieve() throws RetrieveCampaignsException;
}
