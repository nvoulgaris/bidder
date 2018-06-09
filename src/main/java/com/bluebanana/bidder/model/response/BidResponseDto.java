package com.bluebanana.bidder.model.response;

public class BidResponseDto {

  private String id;
  private Bid bid;

  private BidResponseDto(Builder builder) {
    setId(builder.id);
    setBid(builder.bid);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Bid getBid() {
    return bid;
  }

  public void setBid(Bid bid) {
    this.bid = bid;
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
