package pl.nc.javacake.infrastructure.extension;

import org.junit.jupiter.api.AfterEach;
import pl.nc.javacake.infrastructure.jooq.TestJooqOps;
import pl.nc.javacake.infrastructure.jooq.api.JooqModule;

public interface CleanTablesExtension extends
    /* deps */ JooqModule
{
  @AfterEach
  default void cleanDb() {
    TestJooqOps.cleanTables(getDslContext());
  }
}
