package com.bluebanana.bidder.usecase.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class App {

  private final String id;
  private final String name;

  @JsonCreator
  public App(@JsonProperty("id") String id,
             @JsonProperty("name") String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }
}
