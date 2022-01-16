package com.account.qry.domain;

import com.accountcommon.dtos.AccountType;
import com.accountcorecqrs.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccount extends BaseEntity {

  @Id private String id;
  private String accountHolder;
  private LocalDateTime creationDate;
  private AccountType accountType;
  private BigDecimal balance;
}
