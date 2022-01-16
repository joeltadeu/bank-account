package com.accountcorecqrs.handlers;

import com.accountcorecqrs.domain.AggregateRoot;

public interface EventSourcingHandler<T> {
  void save(AggregateRoot aggregate);

  T getById(String id);

  void republishEvents();
}
