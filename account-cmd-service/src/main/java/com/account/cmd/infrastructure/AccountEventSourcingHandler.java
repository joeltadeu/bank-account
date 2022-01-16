package com.account.cmd.infrastructure;

import com.account.cmd.domain.AccountAggregate;
import com.accountcorecqrs.domain.AggregateRoot;
import com.accountcorecqrs.events.BaseEvent;
import com.accountcorecqrs.handlers.EventSourcingHandler;
import com.accountcorecqrs.infrastructure.EventStore;
import com.accountcorecqrs.producers.EventProducer;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class AccountEventSourcingHandler implements EventSourcingHandler<AccountAggregate> {

  private final EventStore eventStore;
  private final EventProducer eventProducer;

  public AccountEventSourcingHandler(EventStore eventStore, EventProducer eventProducer) {
    this.eventStore = eventStore;
    this.eventProducer = eventProducer;
  }

  @Override
  public void save(AggregateRoot aggregate) {
    eventStore.save(aggregate.getId(), aggregate.getUncommittedChanges(), aggregate.getVersion());
    aggregate.markChangesAsCommitted();
  }

  @Override
  public AccountAggregate getById(String id) {
    var aggregate = new AccountAggregate();
    var events = eventStore.getEvents(id);
    if (events != null && !events.isEmpty()) {
      aggregate.replayEvents(events);
      var latestVersion = events.stream().map(BaseEvent::getVersion).max(Comparator.naturalOrder());
      aggregate.setVersion(latestVersion.get());
    }

    return aggregate;
  }

  @Override
  public void republishEvents() {
    var aggregateIds = eventStore.getAggregateIds();
    for (var aggregateId : aggregateIds) {
      var aggregate = getById(aggregateId);
      if (aggregate == null || !aggregate.getActive()) continue;
      var events = eventStore.getEvents(aggregateId);
      for (var event : events) {
        eventProducer.produce(event.getClass().getSimpleName(), event);
      }
    }
  }
}
