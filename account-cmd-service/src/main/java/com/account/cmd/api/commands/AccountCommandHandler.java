package com.account.cmd.api.commands;

import com.account.cmd.domain.AccountAggregate;
import com.accountcorecqrs.handlers.EventSourcingHandler;
import org.springframework.stereotype.Service;

@Service
public class AccountCommandHandler implements CommandHandler {

  private final EventSourcingHandler<AccountAggregate> eventSourcingHandler;

  public AccountCommandHandler(EventSourcingHandler<AccountAggregate> eventSourcingHandler) {
    this.eventSourcingHandler = eventSourcingHandler;
  }

  @Override
  public void handle(OpenAccountCommand command) {
    var aggregate = new AccountAggregate(command);
    eventSourcingHandler.save(aggregate);
  }

  @Override
  public void handle(DepositFundsCommand command) {
    var aggregate = eventSourcingHandler.getById(command.getId());
    aggregate.depositFunds(command.getAmount());
    eventSourcingHandler.save(aggregate);
  }

  @Override
  public void handle(WithdrawFundsCommand command) {
    var aggregate = eventSourcingHandler.getById(command.getId());
    if (command.getAmount().compareTo(aggregate.getBalance()) > 0) {
      throw new IllegalStateException("Withdrawal declined, insufficient funds!");
    }
    aggregate.withdrawFunds(command.getAmount());
    eventSourcingHandler.save(aggregate);
  }

  @Override
  public void handle(CloseAccountCommand command) {
    var aggregate = eventSourcingHandler.getById(command.getId());
    aggregate.closeAccount();
    eventSourcingHandler.save(aggregate);
  }

  @Override
  public void handle(RestoreReadDbCommand command) {
    eventSourcingHandler.republishEvents();
  }
}
