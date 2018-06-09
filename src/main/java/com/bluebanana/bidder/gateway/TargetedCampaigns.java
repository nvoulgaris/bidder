package com.bluebanana.bidder.gateway;

import com.bluebanana.bidder.model.Campaign;
import com.bluebanana.bidder.model.exception.RetrieveCampaignsException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class TargetedCampaigns implements CampaignGateway {

  private static final String GET_ALL_CACMPAIGNS_URI = "http://campaigns.apiblueprint.org/campaigns";

  private ObjectMapper mapper = new ObjectMapper();

  @Autowired private RestTemplate restTemplate;

  @Override
  public List<Campaign> retrieve() throws RetrieveCampaignsException {
    List<Campaign> campaigns;
    try {
      ResponseEntity<String> response = restTemplate.exchange(
          GET_ALL_CACMPAIGNS_URI,
          HttpMethod.GET,
          getHttpEntity(),
          String.class
      );

      campaigns = mapper.readValue(response.getBody(), new TypeReference<List<Campaign>>(){});
    } catch (Exception e) {
      throw new RetrieveCampaignsException("Failed to retrieve campaigns", e);
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
