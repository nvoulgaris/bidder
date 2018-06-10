package com.bluebanana.bidder.gateway;

import com.bluebanana.bidder.JsonFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class TargetedCampaignsGatewayShould {

  private static final String URI = "http://campaigns.apiblueprint.org/campaigns";
  private static final String CAMPAIGNS_RESPONSE = "fixtures/campaign/response/available_campaigns.json";
  private static final String DUMMY_ERROR = "";

  private String campaigns;

  @Mock RestTemplate restTemplate;
  @Mock ResponseEntity<String> campaignsResponse;

  TargetedCampaignsGateway targetedCampaignsGateway;

  @BeforeEach
  public void setUp() {
    initMocks(this);
    JsonFixtures jsonFixtures = new JsonFixtures();
    campaigns = jsonFixtures.load(CAMPAIGNS_RESPONSE);
    targetedCampaignsGateway = new TargetedCampaignsGateway(restTemplate);
  }

  @Test
  public void wrapAndThrowExceptionWhenRetrievalFails() {
    when(restTemplate.exchange(anyString(), any(), any(HttpEntity.class), any(Class.class))).thenThrow(new RestClientException(DUMMY_ERROR));

    Executable retrieveAvailableCampaigns = () -> targetedCampaignsGateway.retrieve();

    assertThrows(RetrieveCampaignsException.class, retrieveAvailableCampaigns);
  }

  @Test
  public void wrapAndThrowExceptionWhenParsingResponseFails() {
    Executable retrieveAvailableCampaigns = () -> targetedCampaignsGateway.retrieve();

    assertThrows(RetrieveCampaignsException.class, retrieveAvailableCampaigns);
  }

  @Test
  public void retrieveAllCampaignsFromTheCampaignApi() {
    when(restTemplate.exchange(anyString(), any(), any(HttpEntity.class), any(Class.class))).thenReturn(campaignsResponse);
    when(campaignsResponse.getBody()).thenReturn(campaigns);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity entity = new HttpEntity(headers);

    targetedCampaignsGateway.retrieve();

    verify(restTemplate).exchange(URI, HttpMethod.GET, entity, String.class);
  }
}