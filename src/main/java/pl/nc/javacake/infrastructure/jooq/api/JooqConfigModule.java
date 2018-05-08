package pl.nc.javacake.infrastructure.jooq.api;

import org.jooq.SQLDialect;

public interface JooqConfigModule {
  JooqConfig getJooqConfig();

  interface JooqConfig {
    SQLDialect getSqlDialect();
  }
}
