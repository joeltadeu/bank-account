package com.accountcorecqrs.producers;

import com.accountcorecqrs.events.BaseEvent;

public interface EventProducer {
  void produce(String topic, BaseEvent event);
}
