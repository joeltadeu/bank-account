package com.accountcorecqrs.infrastructure;

import com.accountcorecqrs.events.BaseEvent;

import java.util.List;

public interface EventStore {

  void save(String aggregateId, Iterable<BaseEvent> events, int expectedVersion);

  List<BaseEvent> getEvents(String aggregateId);

  List<String> getAggregateIds();
}
