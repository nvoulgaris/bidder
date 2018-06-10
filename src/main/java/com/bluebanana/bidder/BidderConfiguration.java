package com.bluebanana.bidder;

import com.bluebanana.bidder.usecase.BidUseCase;
import com.bluebanana.bidder.web.UseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BidderConfiguration {

  @Bean(name = "bidUseCase")
  public UseCase bidUseCase() {
    return new BidUseCase();
  }

  @Bean(name = "restTemplate")
  public RestTemplate restTemplate() {
    return new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
  }
}
