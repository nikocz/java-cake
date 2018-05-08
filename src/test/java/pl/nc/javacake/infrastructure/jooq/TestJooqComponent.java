package pl.nc.javacake.infrastructure.jooq;

import org.jooq.DSLContext;
import pl.nc.javacake.infrastructure.db.TestDataSourceComponent;
import pl.nc.javacake.infrastructure.jooq.api.JooqModule;
import pl.nc.javacake.infrastructure.state.StateComponent;

import static pl.nc.javacake.infrastructure.jooq.TestDslContext.getTestDslContext;

public interface TestJooqComponent extends
    /* impl */ JooqModule,
    /* with */ TestDataSourceComponent, StateComponent
{
  @Override
  default DSLContext getDslContext() {
    return getTestDslContext();
  }
}
