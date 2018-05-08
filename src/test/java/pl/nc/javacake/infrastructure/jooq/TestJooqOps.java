package pl.nc.javacake.infrastructure.jooq;

import lombok.experimental.UtilityClass;
import org.jooq.DDLFlag;
import org.jooq.DSLContext;

import static pl.nc.javacake.jooq.JavaCake.JAVA_CAKE;

@UtilityClass
public class TestJooqOps {

  public static void initDb(final DSLContext dsl) {
    dsl.dropSchemaIfExists(JAVA_CAKE)
        .cascade()
        .execute();
    dsl.ddl(JAVA_CAKE)
        .executeBatch();
  }

  public static void cleanTables(final DSLContext dsl) {
    JAVA_CAKE.tableStream()
        .forEach(table -> dsl.deleteFrom(table).execute());
  }
}
