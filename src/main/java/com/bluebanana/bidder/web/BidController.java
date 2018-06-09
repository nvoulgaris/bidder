package com.bluebanana.bidder.web;

import com.bluebanana.bidder.model.request.BidRequestDto;
import com.bluebanana.bidder.model.BidResponseDto;
import com.bluebanana.bidder.usecase.UseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/bid")
public class BidController {

  private static final String EMPTY_BODY = "";

  @Autowired private UseCase<BidRequestDto,  BidResponseDto> bidUseCase;

  @RequestMapping(
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
  )
  public HttpEntity<BidResponseDto> bid(@RequestBody BidRequestDto bidRequestDto) {
    Optional<BidResponseDto> body = bidUseCase.execute(bidRequestDto);

    ResponseEntity response = body.
        <ResponseEntity>map(responseBody -> new ResponseEntity<>(responseBody, HttpStatus.OK)).
        orElseGet(() -> new ResponseEntity<>(EMPTY_BODY, HttpStatus.NO_CONTENT));

    return response;
  }
}
