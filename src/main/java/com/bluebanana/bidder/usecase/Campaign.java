package com.bluebanana.bidder.usecase;

public interface Campaign {

  String getId();

  double getPrice();

  String getAdm();

  boolean isFor(String country);
}
