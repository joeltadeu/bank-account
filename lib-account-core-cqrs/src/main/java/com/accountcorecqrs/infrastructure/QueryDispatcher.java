package com.accountcorecqrs.infrastructure;

import com.accountcorecqrs.domain.BaseEntity;
import com.accountcorecqrs.queries.BaseQuery;
import com.accountcorecqrs.queries.QueryHandlerMethod;

import java.util.List;

public interface QueryDispatcher {
  <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);

  <U extends BaseEntity> List<U> send(BaseQuery query);
}
