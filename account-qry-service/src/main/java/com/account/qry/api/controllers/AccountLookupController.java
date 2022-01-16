package com.account.qry.api.controllers;

import com.account.qry.api.dtos.EqualityType;
import com.account.qry.api.queries.FindAccountByHolderQuery;
import com.account.qry.api.queries.FindAccountByIdQuery;
import com.account.qry.api.queries.FindAccountsWithBalanceQuery;
import com.account.qry.api.queries.FindAllAccountsQuery;
import com.account.qry.domain.BankAccount;
import com.accountcommon.exceptions.DataNotFoundException;
import com.accountcorecqrs.infrastructure.QueryDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/accounts")
@Slf4j
public class AccountLookupController {

  private final QueryDispatcher queryDispatcher;

  public AccountLookupController(QueryDispatcher queryDispatcher) {
    this.queryDispatcher = queryDispatcher;
  }

  @GetMapping("/")
  public ResponseEntity<List<BankAccount>> getAllAccounts() {
    List<BankAccount> accounts = queryDispatcher.send(new FindAllAccountsQuery());
    return new ResponseEntity<>(accounts, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BankAccount> getAccountById(@PathVariable(value = "id") String id) {
    List<BankAccount> accounts = queryDispatcher.send(new FindAccountByIdQuery(id));
    if (accounts == null || accounts.size() == 0) {
      throw new DataNotFoundException("Bank Account with 'id' %s was not found".formatted(id));
    }
    return new ResponseEntity<>(accounts.get(0), HttpStatus.OK);
  }

  @GetMapping("/byHolder/{accountHolder}")
  public ResponseEntity<List<BankAccount>> getAccountByHolder(
      @PathVariable(value = "accountHolder") String accountHolder) {
    List<BankAccount> accounts = queryDispatcher.send(new FindAccountByHolderQuery(accountHolder));
    return new ResponseEntity<>(accounts, HttpStatus.OK);
  }

  @GetMapping("/byBalance/{equalityType}/{balance}")
  public ResponseEntity<List<BankAccount>> getAccountWithBalance(
      @PathVariable(value = "equalityType") EqualityType equalityType,
      @PathVariable(value = "balance") BigDecimal balance) {
    List<BankAccount> accounts =
        queryDispatcher.send(new FindAccountsWithBalanceQuery(equalityType, balance));
    return new ResponseEntity<>(accounts, HttpStatus.OK);
  }
}
