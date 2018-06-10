package com.bluebanana.bidder.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingAdvisor {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlingAdvisor.class);
  private static final String ERROR_MESSAGE = "Request could not be handled";

  @ExceptionHandler(Exception.class)
  public HttpEntity<String> handleRetrieveCampaignsException(Exception exception) {
    LOGGER.error(ERROR_MESSAGE, exception);
    return new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
