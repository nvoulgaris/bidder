package com.bluebanana.bidder.gateway;

import com.bluebanana.bidder.usecase.Campaign;
import com.bluebanana.bidder.usecase.CampaignGateway;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class TargetedCampaignsGateway implements CampaignGateway {

  private static final String GET_ALL_CAMPAIGNS_URI = "http://campaigns.apiblueprint.org/campaigns";
  private static final String FAILED_TO_RETRIEVE_CAMPAIGNS = "Failed to retrieve campaigns";

  private ObjectMapper mapper;
  private RestTemplate restTemplate;

  @Autowired
  public TargetedCampaignsGateway(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
    this.mapper = new ObjectMapper();
  }

  @Override
  public List<Campaign> retrieve() throws RetrieveCampaignsException {
    List<Campaign> campaigns;
    try {
      ResponseEntity<String> response = restTemplate.exchange(
          GET_ALL_CAMPAIGNS_URI,
          HttpMethod.GET,
          getHttpEntity(),
          String.class
      );

      campaigns = mapper.readValue(response.getBody(), new TypeReference<List<TargetedCampaign>>(){});
    } catch (Exception e) {
      throw new RetrieveCampaignsException(FAILED_TO_RETRIEVE_CAMPAIGNS, e);
    }

    return campaigns;
  }

  private HttpEntity getHttpEntity() {
    return new HttpEntity(getHttpHeaders());
  }

  private HttpHeaders getHttpHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return headers;
  }
}
