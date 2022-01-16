package com.account.cmd.infrastructure;

import com.account.cmd.domain.AccountAggregate;
import com.account.cmd.domain.EventStoreRepository;
import com.accountcommon.exceptions.AggregateNotFoundException;
import com.accountcommon.exceptions.ConcurrencyException;
import com.accountcorecqrs.events.BaseEvent;
import com.accountcorecqrs.events.EventModel;
import com.accountcorecqrs.infrastructure.EventStore;
import com.accountcorecqrs.producers.EventProducer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountEventStore implements EventStore {

  private final EventProducer eventProducer;
  private final EventStoreRepository repository;

  public AccountEventStore(EventProducer eventProducer, EventStoreRepository repository) {
    this.eventProducer = eventProducer;
    this.repository = repository;
  }

  @Override
  public void save(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
    var eventStream = repository.findByAggregateIdentifier(aggregateId);
    if (expectedVersion != -1
        && eventStream.get(eventStream.size() - 1).getVersion() != expectedVersion) {
      throw new ConcurrencyException();
    }
    var version = expectedVersion;
    for (var event : events) {
      version++;
      event.setVersion(version);
      var eventModel =
          EventModel.builder()
              .timestamp(LocalDateTime.now())
              .aggregateIdentifier(aggregateId)
              .aggregateType(AccountAggregate.class.getTypeName())
              .version(version)
              .eventType(event.getClass().getTypeName())
              .eventData(event)
              .build();

      var persistedEvent = repository.save(eventModel);
      if (!persistedEvent.getId().isEmpty()) {
        eventProducer.produce(event.getClass().getSimpleName(), event);
      }
    }
  }

  @Override
  public List<BaseEvent> getEvents(String aggregateId) {
    var eventStream = repository.findByAggregateIdentifier(aggregateId);
    if (eventStream == null || eventStream.isEmpty()) {
      throw new AggregateNotFoundException("Incorrect account ID provided!");
    }

    return eventStream.stream().map(x -> x.getEventData()).collect(Collectors.toList());
  }

  @Override
  public List<String> getAggregateIds() {
    var eventStream = repository.findAll();
    if (eventStream == null || eventStream.isEmpty()) {
      throw new IllegalStateException();
    }
    return eventStream.stream()
        .map(EventModel::getAggregateIdentifier)
        .distinct()
        .collect(Collectors.toList());
  }
}
