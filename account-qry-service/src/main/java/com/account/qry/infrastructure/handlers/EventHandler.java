package com.account.qry.infrastructure.handlers;

import com.accountcommon.events.AccountClosedEvent;
import com.accountcommon.events.AccountOpenedEvent;
import com.accountcommon.events.FundsDepositedEvent;
import com.accountcommon.events.FundsWithdrawnEvent;

public interface EventHandler {
  void on(AccountOpenedEvent event);

  void on(FundsDepositedEvent event);

  void on(FundsWithdrawnEvent event);

  void on(AccountClosedEvent event);
}
