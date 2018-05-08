package pl.nc.javacake.infrastructure.db;

import lombok.experimental.UtilityClass;
import org.h2.jdbcx.JdbcDataSource;

@UtilityClass
public class H2DataSource {
  public static JdbcDataSource getH2DataSource() {
    return H2DataSourceHolder.INSTANCE;
  }

  private static class H2DataSourceHolder {
    private static final String DB_URL = "jdbc:h2:mem:java_cake;DB_CLOSE_DELAY=-1;MODE=MySQL";
    private static final JdbcDataSource INSTANCE = createDataSource();

    private static JdbcDataSource createDataSource() {
      final JdbcDataSource dataSource = new JdbcDataSource();
      dataSource.setUrl(DB_URL);
      return dataSource;
    }
  }
}
