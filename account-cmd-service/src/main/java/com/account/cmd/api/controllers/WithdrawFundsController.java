package com.account.cmd.api.controllers;

import com.account.cmd.api.commands.WithdrawFundsCommand;
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
public class WithdrawFundsController {

  private final CommandDispatcher commandDispatcher;

  public WithdrawFundsController(CommandDispatcher commandDispatcher) {
    this.commandDispatcher = commandDispatcher;
  }

  @PutMapping(path = "/{id}/withdraw")
  public ResponseEntity<BaseResponse> withdrawFunds(
      @PathVariable(value = "id") String id, @RequestBody WithdrawFundsCommand command) {
    command.setId(id);
    commandDispatcher.send(command);
    return new ResponseEntity<>(
        new BaseResponse("Withdraw funds request completed successfully"), HttpStatus.OK);
  }
}
