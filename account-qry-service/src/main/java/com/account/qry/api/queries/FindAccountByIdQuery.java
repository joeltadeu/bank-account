package com.account.qry.api.queries;

import com.accountcorecqrs.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountByIdQuery extends BaseQuery {
  private String id;
}
