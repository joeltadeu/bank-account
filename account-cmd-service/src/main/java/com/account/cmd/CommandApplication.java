package com.account.cmd;

import com.account.cmd.api.commands.CloseAccountCommand;
import com.account.cmd.api.commands.CommandHandler;
import com.account.cmd.api.commands.DepositFundsCommand;
import com.account.cmd.api.commands.OpenAccountCommand;
import com.account.cmd.api.commands.RestoreReadDbCommand;
import com.account.cmd.api.commands.WithdrawFundsCommand;
import com.accountcorecqrs.infrastructure.CommandDispatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CommandApplication {

  private final CommandDispatcher commandDispatcher;
  private final CommandHandler commandHandler;

  public CommandApplication(CommandDispatcher commandDispatcher, CommandHandler commandHandler) {
    this.commandDispatcher = commandDispatcher;
    this.commandHandler = commandHandler;
  }

  public static void main(String[] args) {
    SpringApplication.run(CommandApplication.class, args);
  }

  @PostConstruct
  public void registerHandlers() {
    commandDispatcher.registerHandler(OpenAccountCommand.class, commandHandler::handle);
    commandDispatcher.registerHandler(DepositFundsCommand.class, commandHandler::handle);
    commandDispatcher.registerHandler(WithdrawFundsCommand.class, commandHandler::handle);
    commandDispatcher.registerHandler(CloseAccountCommand.class, commandHandler::handle);
    commandDispatcher.registerHandler(RestoreReadDbCommand.class, commandHandler::handle);
  }
}
