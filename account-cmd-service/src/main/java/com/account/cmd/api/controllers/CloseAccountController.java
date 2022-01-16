package com.account.cmd.api.controllers;

import com.account.cmd.api.commands.CloseAccountCommand;
import com.accountcommon.dtos.BaseResponse;
import com.accountcorecqrs.infrastructure.CommandDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/accounts")
@Slf4j
public class CloseAccountController {

  private final CommandDispatcher commandDispatcher;

  public CloseAccountController(CommandDispatcher commandDispatcher) {
    this.commandDispatcher = commandDispatcher;
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<BaseResponse> closeAccount(@PathVariable(value = "id") String id) {
    commandDispatcher.send(new CloseAccountCommand(id));
    return new ResponseEntity<>(
        new BaseResponse("Bank account closure request successfully completed!"), HttpStatus.OK);
  }
}
