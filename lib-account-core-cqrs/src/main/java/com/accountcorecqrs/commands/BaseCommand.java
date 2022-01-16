package com.accountcorecqrs.commands;

import com.accountcorecqrs.messages.Message;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseCommand extends Message {

  public BaseCommand(String id) {
    super(id);
  }
}
