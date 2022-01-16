package com.accountcommon.exceptions;

public class AggregateNotFoundException extends RuntimeException {
  public AggregateNotFoundException(String message) {
    super(message);
  }
}
