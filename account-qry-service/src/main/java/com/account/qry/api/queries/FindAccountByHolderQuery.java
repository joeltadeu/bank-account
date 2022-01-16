package com.account.qry.api.queries;

import com.accountcorecqrs.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountByHolderQuery extends BaseQuery {
  private String accountHolder;
}
