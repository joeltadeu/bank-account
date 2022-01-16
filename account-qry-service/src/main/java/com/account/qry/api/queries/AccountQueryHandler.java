package com.account.qry.api.queries;

import com.account.qry.api.dtos.EqualityType;
import com.account.qry.domain.BankAccount;
import com.account.qry.domain.BankAccountRepository;
import com.accountcorecqrs.domain.BaseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AccountQueryHandler implements QueryHandler {

  private final BankAccountRepository repository;

  public AccountQueryHandler(BankAccountRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<BaseEntity> handle(FindAllAccountsQuery query) {
    Iterable<BankAccount> bankAccounts = repository.findAll();
    List<BaseEntity> bankAccountList = new ArrayList<>();
    bankAccounts.forEach(bankAccountList::add);
    return bankAccountList;
  }

  @Override
  public List<BaseEntity> handle(FindAccountByIdQuery query) {
    var bankAccount = repository.findById(query.getId());
    if (bankAccount.isEmpty()) {
      return null;
    }

    return Arrays.asList(bankAccount.get());
  }

  @Override
  public List<BaseEntity> handle(FindAccountByHolderQuery query) {
    var bankAccount = repository.findBankAccountByAccountHolder(query.getAccountHolder());
    if (bankAccount.isEmpty()) {
      return null;
    }

    return Arrays.asList(bankAccount.get());
  }

  @Override
  public List<BaseEntity> handle(FindAccountsWithBalanceQuery query) {
    return query.getEqualityType() == EqualityType.GREATER_THAN
        ? repository.findByBalanceGreaterThan(query.getBalance())
        : repository.findByBalanceLessThan(query.getBalance());
  }
}
