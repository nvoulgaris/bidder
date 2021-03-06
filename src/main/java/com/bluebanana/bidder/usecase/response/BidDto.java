package com.bluebanana.bidder.usecase.response;

public class BidDto {

  private final String campaignId;
  private final double price;
  private final String adm;

  private BidDto(Builder builder) {
    campaignId = builder.campaignId;
    price = builder.price;
    adm = builder.adm;

  }

  public String getCampaignId() {
    return campaignId;
  }

  public double getPrice() {
    return price;
  }

  public String getAdm() {
    return adm;
  }

  public static final class Builder {
    private String campaignId;
    private double price;
    private String adm;

    public Builder() {
    }

    public Builder withCampaignId(String campaignId) {
      this.campaignId = campaignId;
      return this;
    }

    public Builder withPrice(double price) {
      this.price = price;
      return this;
    }

    public Builder withAdm(String adm) {
      this.adm = adm;
      return this;
    }

    public BidDto build() {
      return new BidDto(this);
    }
  }
}
