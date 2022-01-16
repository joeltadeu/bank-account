package com.account.cmd.api.commands;

import com.accountcommon.dtos.AccountType;
import com.accountcorecqrs.commands.BaseCommand;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OpenAccountCommand extends BaseCommand {

  private String accountHolder;
  private AccountType accountType;
  private BigDecimal balance;
}
