package com.account.qry.domain;

import com.accountcorecqrs.domain.BaseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount, String> {
  Optional<BankAccount> findBankAccountByAccountHolder(String accountHolder);

  List<BaseEntity> findByBalanceGreaterThan(BigDecimal balance);

  List<BaseEntity> findByBalanceLessThan(BigDecimal balance);
}
