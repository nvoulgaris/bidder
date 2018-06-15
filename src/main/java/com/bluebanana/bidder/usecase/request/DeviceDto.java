package com.bluebanana.bidder.usecase.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceDto {

  private final String os;
  private final GeoDto geoDto;

  @JsonCreator
  public DeviceDto(@JsonProperty("os") String os,
                   @JsonProperty("geo") GeoDto geoDto) {
    this.os = os;
    this.geoDto = geoDto;
  }

  public GeoDto getGeoDto() {
    return geoDto;
  }
}
