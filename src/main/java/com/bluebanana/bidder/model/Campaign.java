package com.bluebanana.bidder.model;

import java.util.List;

public class Campaign {

  private String id;
  private String name;
  private double price;
  private String adm;
  private List<String> targetedCountries;

  public Campaign() {
  }

  public Campaign(String id, String name, double price, String adm, List<String> targetedCountries) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.adm = adm;
    this.targetedCountries = targetedCountries;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getAdm() {
    return adm;
  }

  public void setAdm(String adm) {
    this.adm = adm;
  }

  public List<String> getTargetedCountries() {
    return targetedCountries;
  }

  public void setTargetedCountries(List<String> targetedCountries) {
    this.targetedCountries = targetedCountries;
  }
}
