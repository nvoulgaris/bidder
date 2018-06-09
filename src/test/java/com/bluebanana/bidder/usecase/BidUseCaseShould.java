package com.bluebanana.bidder.usecase;

import com.bluebanana.bidder.gateway.CampaignGateway;
import com.bluebanana.bidder.model.BidRequestDto;
import com.bluebanana.bidder.model.BidResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BidUseCaseShould {

  @Mock BidRequestDto request;
  @Mock CampaignGateway targetedCampaigns;
  @InjectMocks BidUseCase useCase;

  @BeforeEach
  public void setUp() {
    initMocks(this);
  }

  @Test
  public void returnEmptyResponseWhenNoAvailableCampaignsAreRetrieved() {
    when(targetedCampaigns.retrieve()).thenReturn(Collections.EMPTY_LIST);
    Optional<BidResponseDto> response = useCase.execute(request);
    assertThat(response).isEqualTo(Optional.empty());
  }
}