package com.account.cmd.domain;

import com.account.cmd.api.commands.OpenAccountCommand;
import com.accountcommon.events.AccountClosedEvent;
import com.accountcommon.events.AccountOpenedEvent;
import com.accountcommon.events.FundsDepositedEvent;
import com.accountcommon.events.FundsWithdrawnEvent;
import com.accountcorecqrs.domain.AggregateRoot;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {

  private Boolean active;
  private BigDecimal balance;

  public AccountAggregate(OpenAccountCommand command) {
    raiseEvent(
        AccountOpenedEvent.builder()
            .id(command.getId())
            .accountHolder(command.getAccountHolder())
            .createdDate(LocalDateTime.now())
            .accountType(command.getAccountType())
            .balance(command.getBalance())
            .build());
  }

  public BigDecimal getBalance() {
    return this.balance;
  }

  public Boolean getActive() {
    return this.active;
  }

  public void apply(AccountOpenedEvent event) {
    this.id = event.getId();
    this.active = true;
    this.balance = event.getBalance();
  }

  public void depositFunds(BigDecimal amount) {
    if (!this.active) {
      throw new IllegalStateException("Funds cannot be deposited into a closed account!");
    }

    if (amount.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalStateException("The deposit amount must be greater than 0!");
    }

    raiseEvent(FundsDepositedEvent.builder().id(this.id).amount(amount).build());
  }

  public void apply(FundsDepositedEvent event) {
    this.id = event.getId();
    this.balance = this.balance.add(event.getAmount());
  }

  public void withdrawFunds(BigDecimal amount) {
    if (!this.active) {
      throw new IllegalStateException("Funds cannot be withdrawn into a closed account!");
    }
    raiseEvent(FundsWithdrawnEvent.builder().id(this.id).amount(amount).build());
  }

  public void apply(FundsWithdrawnEvent event) {
    this.id = event.getId();
    this.balance = this.balance.subtract(event.getAmount());
  }

  public void closeAccount() {
    if (!this.active) {
      throw new IllegalStateException("The bank account has already been closed!");
    }
    raiseEvent(AccountClosedEvent.builder().id(this.id).build());
  }

  public void apply(AccountClosedEvent event) {
    this.id = event.getId();
    this.active = false;
  }
}
