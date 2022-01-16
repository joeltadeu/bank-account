package com.account.qry.api.queries;

import com.accountcorecqrs.domain.BaseEntity;

import java.util.List;

public interface QueryHandler {
  List<BaseEntity> handle(FindAllAccountsQuery query);

  List<BaseEntity> handle(FindAccountByIdQuery query);

  List<BaseEntity> handle(FindAccountByHolderQuery query);

  List<BaseEntity> handle(FindAccountsWithBalanceQuery query);
}
