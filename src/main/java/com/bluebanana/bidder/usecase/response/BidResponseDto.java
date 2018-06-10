package com.bluebanana.bidder.usecase.response;

import com.bluebanana.bidder.web.ResponseDto;

public class BidResponseDto implements ResponseDto {

  private final String id;
  private final Bid bid;

  private BidResponseDto(Builder builder) {
    id = builder.id;
    bid = builder.bid;
  }

  public String getId() {
    return id;
  }

  public Bid getBid() {
    return bid;
  }

  public static final class Builder {
    private String id;
    private Bid bid;

    public Builder() {
    }

    public Builder withId(String id) {
      this.id = id;
      return this;
    }

    public Builder withBid(Bid bid) {
      this.bid = bid;
      return this;
    }

    public BidResponseDto build() {
      return new BidResponseDto(this);
    }
  }
}
