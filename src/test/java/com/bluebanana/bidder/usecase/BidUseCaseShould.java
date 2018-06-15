package com.bluebanana.bidder.usecase;

import com.bluebanana.bidder.usecase.request.BidRequestDto;
import com.bluebanana.bidder.usecase.response.BidResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BidUseCaseShould {

  private CampaignConverter campaignConverter;

  @Mock Campaign campaignUsa110;
  @Mock Campaign campaignUsa120;
  @Mock Campaign campaignUsa130;
  @Mock Campaign campaignMex105;
  @Mock BidRequestDto request;
  @Mock CampaignGateway targetedCampaignsGateway;

  BidUseCase useCase;

  private void prepareMocksBehavior() {
    when(request.country()).thenReturn("USA");
    when(campaignUsa110.isFor("USA")).thenReturn(true);
    when(campaignUsa110.getPrice()).thenReturn(1.10);
    when(campaignUsa120.isFor("USA")).thenReturn(true);
    when(campaignUsa120.getPrice()).thenReturn(1.20);
    when(campaignUsa130.isFor("USA")).thenReturn(true);
    when(campaignUsa130.getPrice()).thenReturn(1.30);
    when(campaignMex105.isFor("MEX")).thenReturn(true);
    when(campaignMex105.getPrice()).thenReturn(1.05);
    when(targetedCampaignsGateway.retrieve()).thenReturn(new ArrayList<>(Arrays.asList(campaignUsa110, campaignUsa120, campaignUsa130)));
  }

  @BeforeEach
  public void setUp() {
    initMocks(this);
    prepareMocksBehavior();
    campaignConverter = new CampaignConverter();
    useCase = new BidUseCase(targetedCampaignsGateway, campaignConverter);
  }

  @Test
  public void returnEmptyResponseWhenNoAvailableCampaignsAreRetrieved() {
    when(targetedCampaignsGateway.retrieve()).thenReturn(Collections.EMPTY_LIST);

    Optional<BidResponseDto> response = useCase.execute(request);

    assertThat(response).isEqualTo(Optional.empty());
  }

  @Test
  public void returnEmptyResponseWhenNoMatchingCampaignsAreRetrieved() {
    when(request.country()).thenReturn("CYP");

    Optional<BidResponseDto> response = useCase.execute(request);

    assertThat(response).isEqualTo(Optional.empty());
  }

  @Test
  public void returnACampaignWhenThereIsAtLeastAMatchingCampaign() {
    when(request.country()).thenReturn("MEX");
    when(targetedCampaignsGateway.retrieve()).thenReturn(new ArrayList<>(Arrays.asList(campaignUsa110, campaignUsa120, campaignMex105)));

    Optional<BidResponseDto> response = useCase.execute(request);

    assertThat(response.isPresent()).isTrue();
  }

  @Test
  public void returnMatchingCampaignWhenThereIsOnlyOneMatching() {
    when(request.country()).thenReturn("MEX");
    when(targetedCampaignsGateway.retrieve()).thenReturn(new ArrayList<>(Arrays.asList(campaignUsa110, campaignUsa120, campaignMex105)));

    Optional<BidResponseDto> response = useCase.execute(request);

    assertThat(response.get().getBidDto().getPrice()).isEqualTo(1.05);
  }

  @Test
  public void returnHighestPayingCampaignWhenThereAreMultipleMatchingCampaigns() {
    Optional<BidResponseDto> response = useCase.execute(request);

    assertThat(response.get().getBidDto().getPrice()).isEqualTo(1.3);
  }

  @Test
  public void returnOneOfTheHighestPayingCampaignWhenThereAreMultipleMatchingHighestPayingCampaigns() {
    when(campaignUsa120.getPrice()).thenReturn(1.40);
    when(campaignUsa130.getPrice()).thenReturn(1.40);

    Optional<BidResponseDto> response = useCase.execute(request);

    assertThat(response.get().getBidDto().getPrice()).isEqualTo(1.4);
  }
}