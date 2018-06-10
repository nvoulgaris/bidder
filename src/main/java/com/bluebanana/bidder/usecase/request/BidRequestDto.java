package com.bluebanana.bidder.usecase.request;

import com.bluebanana.bidder.web.RequestDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BidRequestDto implements RequestDto {

  private final String id;
  private final App app;
  private final Device device;

  @JsonCreator
  public BidRequestDto(@JsonProperty("id") String id,
                       @JsonProperty("app") App app,
                       @JsonProperty("device") Device device) {
    this.id = id;
    this.app = app;
    this.device = device;
  }

  public String getId() {
    return id;
  }

  public Device getDevice() {
    return device;
  }

  public String country() {
    return this.getDevice().getGeo().getCountry();
  }
}
