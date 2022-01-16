package com.account.cmd.api.commands;

import com.accountcorecqrs.commands.BaseCommand;

public class CloseAccountCommand extends BaseCommand {

  public CloseAccountCommand(String id) {
    super(id);
  }
}
