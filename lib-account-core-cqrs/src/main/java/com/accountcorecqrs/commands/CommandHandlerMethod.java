package com.accountcorecqrs.commands;

@FunctionalInterface
public interface CommandHandlerMethod<T extends BaseCommand> {
  void handle(T command);
}
