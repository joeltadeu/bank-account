package com.account.cmd.infrastructure;

import com.accountcorecqrs.events.BaseEvent;
import com.accountcorecqrs.producers.EventProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountEventProducer implements EventProducer {

  private final KafkaTemplate<String, Object> kafkaTemplate;

  public AccountEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @Override
  public void produce(String topic, BaseEvent event) {
    this.kafkaTemplate.send(topic, event);
  }
}
