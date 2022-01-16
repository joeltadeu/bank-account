package com.account.qry.api.queries;

import com.account.qry.api.dtos.EqualityType;
import com.accountcorecqrs.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class FindAccountsWithBalanceQuery extends BaseQuery {
  private EqualityType equalityType;
  private BigDecimal balance;
}
