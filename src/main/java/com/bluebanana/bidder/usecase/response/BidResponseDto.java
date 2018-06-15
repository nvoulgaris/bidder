package com.bluebanana.bidder.usecase.response;

import com.bluebanana.bidder.web.ResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BidResponseDto implements ResponseDto {

  private final String id;

  @JsonProperty("bid")
  private final BidDto bidDto;

  private BidResponseDto(Builder builder) {
    id = builder.id;
    bidDto = builder.bidDto;
  }

  public String getId() {
    return id;
  }

  public BidDto getBidDto() {
    return bidDto;
  }

  public static final class Builder {
    private String id;
    private BidDto bidDto;

    public Builder() {
    }

    public Builder withId(String id) {
      this.id = id;
      return this;
    }

    public Builder withBid(BidDto bidDto) {
      this.bidDto = bidDto;
      return this;
    }

    public BidResponseDto build() {
      return new BidResponseDto(this);
    }
  }
}
