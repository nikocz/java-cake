package pl.nc.javacake.infrastructure.jooq;

import lombok.experimental.UtilityClass;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import pl.nc.javacake.infrastructure.db.H2DataSource;

import static pl.nc.javacake.infrastructure.jooq.TestJooqOps.initDb;

@UtilityClass
public class TestDslContext {
  public static DSLContext getTestDslContext() {
    return TestDslContextHolder.INSTANCE;
  }

  private static class TestDslContextHolder {
    private static final DSLContext INSTANCE = createDslContext();

    private static DSLContext createDslContext() {
      final DSLContext dsl = DSL.using(H2DataSource.getH2DataSource(), SQLDialect.H2);
      initDb(dsl);
      return dsl;
    }
  }
}
