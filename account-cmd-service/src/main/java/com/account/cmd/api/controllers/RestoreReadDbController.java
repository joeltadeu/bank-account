package com.account.cmd.api.controllers;

import com.account.cmd.api.commands.RestoreReadDbCommand;
import com.accountcommon.dtos.BaseResponse;
import com.accountcorecqrs.infrastructure.CommandDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/operations/database/restore")
@Slf4j
public class RestoreReadDbController {
  private final CommandDispatcher commandDispatcher;

  public RestoreReadDbController(CommandDispatcher commandDispatcher) {
    this.commandDispatcher = commandDispatcher;
  }

  @PostMapping
  public ResponseEntity<BaseResponse> restoreReadDb() {
    commandDispatcher.send(new RestoreReadDbCommand());
    return new ResponseEntity<>(
        new BaseResponse("Read database restore request completed successfully!"),
        HttpStatus.CREATED);
  }
}
