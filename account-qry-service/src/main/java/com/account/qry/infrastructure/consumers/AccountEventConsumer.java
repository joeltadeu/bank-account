package com.account.qry.infrastructure.consumers;

import com.account.qry.infrastructure.handlers.EventHandler;
import com.accountcommon.events.AccountClosedEvent;
import com.accountcommon.events.AccountOpenedEvent;
import com.accountcommon.events.FundsDepositedEvent;
import com.accountcommon.events.FundsWithdrawnEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class AccountEventConsumer implements EventConsumer {

  private final EventHandler eventHandler;

  public AccountEventConsumer(EventHandler eventHandler) {
    this.eventHandler = eventHandler;
  }

  @KafkaListener(topics = "AccountOpenedEvent", groupId = "${spring.kafka.consumer.group-id}")
  @Override
  public void consume(AccountOpenedEvent event, Acknowledgment ack) {
    eventHandler.on(event);
    ack.acknowledge();
  }

  @KafkaListener(topics = "FundsDepositedEvent", groupId = "${spring.kafka.consumer.group-id}")
  @Override
  public void consume(FundsDepositedEvent event, Acknowledgment ack) {
    eventHandler.on(event);
    ack.acknowledge();
  }

  @KafkaListener(topics = "FundsWithdrawnEvent", groupId = "${spring.kafka.consumer.group-id}")
  @Override
  public void consume(FundsWithdrawnEvent event, Acknowledgment ack) {
    eventHandler.on(event);
    ack.acknowledge();
  }

  @KafkaListener(topics = "AccountClosedEvent", groupId = "${spring.kafka.consumer.group-id}")
  @Override
  public void consume(AccountClosedEvent event, Acknowledgment ack) {
    eventHandler.on(event);
    ack.acknowledge();
  }
}
