package com.bluebanana.bidder.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Device {

  private final String os;
  private final Geo geo;

  @JsonCreator
  public Device(@JsonProperty("os") String os,
                @JsonProperty("geo") Geo geo) {
    this.os = os;
    this.geo = geo;
  }

  public Geo getGeo() {
    return geo;
  }
}
