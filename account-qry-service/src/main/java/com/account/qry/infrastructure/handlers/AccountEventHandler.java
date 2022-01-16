package com.account.qry.infrastructure.handlers;

import com.account.qry.domain.BankAccount;
import com.account.qry.domain.BankAccountRepository;
import com.accountcommon.events.AccountClosedEvent;
import com.accountcommon.events.AccountOpenedEvent;
import com.accountcommon.events.FundsDepositedEvent;
import com.accountcommon.events.FundsWithdrawnEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccountEventHandler implements EventHandler {

  private final BankAccountRepository repository;

  public AccountEventHandler(BankAccountRepository repository) {
    this.repository = repository;
  }

  @Override
  public void on(AccountOpenedEvent event) {
    var bankAccount =
        BankAccount.builder()
            .id(event.getId())
            .accountHolder(event.getAccountHolder())
            .accountType(event.getAccountType())
            .balance(event.getBalance())
            .creationDate(LocalDateTime.now())
            .build();

    repository.save(bankAccount);
  }

  @Override
  public void on(FundsDepositedEvent event) {
    var bankAccount = repository.findById(event.getId());
    if (bankAccount.isEmpty()) return;

    var currentBalance = bankAccount.get().getBalance();
    var latestBalance = currentBalance.add(event.getAmount());
    bankAccount.get().setBalance(latestBalance);
    repository.save(bankAccount.get());
  }

  @Override
  public void on(FundsWithdrawnEvent event) {
    var bankAccount = repository.findById(event.getId());
    if (bankAccount.isEmpty()) return;

    var currentBalance = bankAccount.get().getBalance();
    var latestBalance = currentBalance.subtract(event.getAmount());
    bankAccount.get().setBalance(latestBalance);
    repository.save(bankAccount.get());
  }

  @Override
  public void on(AccountClosedEvent event) {
    repository.deleteById(event.getId());
  }
}
