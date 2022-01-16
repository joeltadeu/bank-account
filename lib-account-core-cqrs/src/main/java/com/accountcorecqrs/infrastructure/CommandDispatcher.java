package com.accountcorecqrs.infrastructure;

import com.accountcorecqrs.commands.BaseCommand;
import com.accountcorecqrs.commands.CommandHandlerMethod;

public interface CommandDispatcher {

  <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);

  void send(BaseCommand command);
}
