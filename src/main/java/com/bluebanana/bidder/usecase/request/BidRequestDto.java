package com.bluebanana.bidder.usecase.request;

import com.bluebanana.bidder.web.RequestDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BidRequestDto implements RequestDto {

  private final String id;
  private final AppDto appDto;
  private final DeviceDto deviceDto;

  @JsonCreator
  public BidRequestDto(@JsonProperty("id") String id,
                       @JsonProperty("app") AppDto appDto,
                       @JsonProperty("device") DeviceDto deviceDto) {
    this.id = id;
    this.appDto = appDto;
    this.deviceDto = deviceDto;
  }

  public String getId() {
    return id;
  }

  public DeviceDto getDeviceDto() {
    return deviceDto;
  }

  public String country() {
    return this.getDeviceDto().getGeoDto().getCountry();
  }
}
