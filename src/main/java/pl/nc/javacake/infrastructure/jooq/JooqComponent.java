package pl.nc.javacake.infrastructure.jooq;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import pl.nc.javacake.infrastructure.db.api.DataSourceModule;
import pl.nc.javacake.infrastructure.jooq.api.JooqConfigModule;
import pl.nc.javacake.infrastructure.jooq.api.JooqModule;

public interface JooqComponent extends
    /* impl */ JooqModule,
    /* deps */ DataSourceModule, JooqConfigModule
{
  @Override
  default DSLContext getDslContext() {
    return DSL.using(getDataSource(), getJooqConfig().getSqlDialect());
  }
}
