package com.bluebanana.bidder.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Geo {

  private final String country;
  private final Long lat;
  private final Long lon;

  @JsonCreator
  public Geo(@JsonProperty("country") String country,
             @JsonProperty("lat") Long lat,
             @JsonProperty("lon") Long lon) {
    this.country = country;
    this.lat = lat;
    this.lon = lon;
  }

  public String getCountry() {
    return country;
  }
}
