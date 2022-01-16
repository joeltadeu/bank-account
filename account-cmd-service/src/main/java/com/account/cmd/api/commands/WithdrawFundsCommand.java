package com.account.cmd.api.commands;

import com.accountcorecqrs.commands.BaseCommand;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawFundsCommand extends BaseCommand {

  private BigDecimal amount;
}
