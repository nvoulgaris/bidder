package com.bluebanana.bidder.gateway;

import com.bluebanana.bidder.model.exception.RetrieveCampaignsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class TargetedCampaignsShould {

  private static final String URI = "http://campaigns.apiblueprint.org/campaigns";
  private static final String DUMMY_ERROR = "";

  @Mock RestTemplate restTemplate;
  @Mock ObjectMapper mapper;
  @Mock ResponseEntity<String> campaignsResponse;
  @InjectMocks TargetedCampaigns targetedCampaigns;

  @BeforeEach
  public void setUp() {
    initMocks(this);
  }

  @Test
  public void wrapAndThrowExceptionWhenRetrievalFails() {
    when(restTemplate.exchange(anyString(), any(), any(HttpEntity.class), any(Class.class))).thenThrow(new RestClientException(DUMMY_ERROR));

    Executable retrieveAvailableCampaigns = () -> targetedCampaigns.retrieve();

    assertThrows(RetrieveCampaignsException.class, retrieveAvailableCampaigns);
  }

  @Test
  public void wrapAndThrowExceptionWhenParsingResponseFails() throws Exception {
    when(mapper.readValue(any(String.class), any(Class.class))).thenThrow(new IOException());

    Executable retrieveAvailableCampaigns = () -> targetedCampaigns.retrieve();

    assertThrows(RetrieveCampaignsException.class, retrieveAvailableCampaigns);
  }

  @Test
  public void retrieveAllCampaignsFromTheCampaignApi() {
    when(restTemplate.exchange(anyString(), any(), any(HttpEntity.class), any(Class.class))).thenReturn(campaignsResponse);

    targetedCampaigns.retrieve();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity entity = new HttpEntity(headers);

    verify(restTemplate).exchange(URI, HttpMethod.GET, entity, String.class);
  }
}