package com.account.qry;

import com.account.qry.api.queries.FindAccountByHolderQuery;
import com.account.qry.api.queries.FindAccountByIdQuery;
import com.account.qry.api.queries.FindAccountsWithBalanceQuery;
import com.account.qry.api.queries.FindAllAccountsQuery;
import com.account.qry.api.queries.QueryHandler;
import com.accountcorecqrs.infrastructure.QueryDispatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class QueryApplication {

  private final QueryDispatcher queryDispatcher;
  private final QueryHandler queryHandler;

  public QueryApplication(QueryDispatcher queryDispatcher, QueryHandler queryHandler) {
    this.queryDispatcher = queryDispatcher;
    this.queryHandler = queryHandler;
  }

  public static void main(String[] args) {
    SpringApplication.run(QueryApplication.class, args);
  }

  @PostConstruct
  public void registerHandlers() {
    queryDispatcher.registerHandler(FindAllAccountsQuery.class, queryHandler::handle);
    queryDispatcher.registerHandler(FindAccountByIdQuery.class, queryHandler::handle);
    queryDispatcher.registerHandler(FindAccountByHolderQuery.class, queryHandler::handle);
    queryDispatcher.registerHandler(FindAccountsWithBalanceQuery.class, queryHandler::handle);
  }
}
