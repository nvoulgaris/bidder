package com.bluebanana.bidder.web;

import com.bluebanana.bidder.usecase.request.BidRequestDto;
import com.bluebanana.bidder.usecase.response.BidResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BidControllerShould {

  @Mock UseCase<RequestDto, ResponseDto> useCase;
  @Mock BidRequestDto bidRequestDto;
  @Mock BidResponseDto useCaseResponse;

  BidController bidController;

  private void prepareMocksBehavior() {
    when(useCase.execute(bidRequestDto)).thenReturn(Optional.of(useCaseResponse));
  }

  @BeforeEach
  public void setUp() {
    initMocks(this);
    prepareMocksBehavior();
    bidController = new BidController(useCase);
  }

  @Test
  public void triggerBidUseCaseDelegatingTheRequest() {
    bidController.bid(bidRequestDto);

    verify(useCase).execute(bidRequestDto);
  }

  @Test
  public void propagateTheUseCaseResponseWithStatusOKWhenThereIsAResponse() {
    ResponseEntity<BidResponseDto> expectedResponse = new ResponseEntity<>(useCaseResponse, HttpStatus.OK);

    HttpEntity<BidResponseDto> response = bidController.bid(bidRequestDto);

    assertThat(response).isEqualTo(expectedResponse);
  }

  @Test
  public void propagateTheUseCaseResponseWithStatusNoContentWhenTheResponseIsEmpty() {
    ResponseEntity<String> expectedResponse = new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    when(useCase.execute(bidRequestDto)).thenReturn(Optional.empty());

    HttpEntity<BidResponseDto> response = bidController.bid(bidRequestDto);

    assertThat(response).isEqualTo(expectedResponse);
  }
}