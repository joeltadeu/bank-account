package com.account.cmd.api.controllers;

import com.account.cmd.api.commands.OpenAccountCommand;
import com.account.cmd.api.dtos.OpenAccountResponse;
import com.accountcommon.dtos.BaseResponse;
import com.accountcorecqrs.infrastructure.CommandDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/accounts")
@Slf4j
public class OpenAccountController {

  private final CommandDispatcher commandDispatcher;

  public OpenAccountController(CommandDispatcher commandDispatcher) {
    this.commandDispatcher = commandDispatcher;
  }

  @PostMapping
  public ResponseEntity<BaseResponse> openAccount(@RequestBody OpenAccountCommand command) {
    var id = UUID.randomUUID().toString();
    command.setId(id);
    commandDispatcher.send(command);
    return new ResponseEntity<>(
        new OpenAccountResponse("Bank account creation request completed successfully", id),
        HttpStatus.CREATED);
  }
}
