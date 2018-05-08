package pl.nc.javacake.infrastructure.db;

import org.h2.jdbcx.JdbcDataSource;
import pl.nc.javacake.infrastructure.db.api.DataSourceModule;

import static pl.nc.javacake.infrastructure.db.H2DataSource.getH2DataSource;

public interface TestDataSourceComponent extends
    /* impl */ DataSourceModule
{
  @Override
  default JdbcDataSource getDataSource() {
    return getH2DataSource();
  }
}
