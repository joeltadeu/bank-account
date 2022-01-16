package com.account.cmd.api.controllers;

import com.account.cmd.api.commands.DepositFundsCommand;
import com.accountcommon.dtos.BaseResponse;
import com.accountcorecqrs.infrastructure.CommandDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/accounts")
@Slf4j
public class DepositFundsController {

  private final CommandDispatcher commandDispatcher;

  public DepositFundsController(CommandDispatcher commandDispatcher) {
    this.commandDispatcher = commandDispatcher;
  }

  @PutMapping(path = "/{id}/deposit")
  public ResponseEntity<BaseResponse> depositFunds(
      @PathVariable(value = "id") String id, @RequestBody DepositFundsCommand command) {
    command.setId(id);
    commandDispatcher.send(command);
    return new ResponseEntity<>(
        new BaseResponse("Deposit funds request completed successfully"), HttpStatus.OK);
  }
}
