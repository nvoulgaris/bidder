package com.bluebanana.bidder.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Campaign {

  private final String id;
  private final String name;
  private final double price;
  private final String adm;
  private final List<String> targetedCountries;

  @JsonCreator
  public Campaign(@JsonProperty("id") String id,
                  @JsonProperty("name") String name,
                  @JsonProperty("price") double price,
                  @JsonProperty("adm") String adm,
                  @JsonProperty("targetedCountries") List<String> targetedCountries
  ) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.adm = adm;
    this.targetedCountries = targetedCountries;
  }

  public String getId() {
    return id;
  }

  public double getPrice() {
    return price;
  }

  public String getAdm() {
    return adm;
  }

  public boolean isFor(String country) {
    return this.targetedCountries.contains(country);
  }
}
