package pl.nc.javacake.infrastructure.db.api;

import javax.sql.DataSource;

public interface DataSourceModule {
  DataSource getDataSource();
}
