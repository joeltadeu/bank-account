package com.accountcommon.events;

import com.accountcommon.dtos.AccountType;
import com.accountcorecqrs.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AccountOpenedEvent extends BaseEvent {
  private String accountHolder;
  private AccountType accountType;
  private LocalDateTime createdDate;
  private BigDecimal balance;
}
