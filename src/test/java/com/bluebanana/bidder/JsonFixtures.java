package com.bluebanana.bidder;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;

public class JsonFixtures {

  public String load(String path) {
    String json;
    try {
      json = Resources.toString(Resources.getResource(path), Charsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException("Could not load json from " + path);
    }
    return json;
  }
}
