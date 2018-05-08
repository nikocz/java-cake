package pl.nc.javacake.infrastructure.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import pl.nc.javacake.infrastructure.db.api.DataSourceConfigModule;
import pl.nc.javacake.infrastructure.db.api.DataSourceModule;
import pl.nc.javacake.infrastructure.state.StateComponent;

public interface DataSourceComponent extends
    /* impl */ DataSourceModule,
    /* deps */ DataSourceConfigModule,
    /* with */ StateComponent
{
  Field<DataSourceComponent, HikariDataSource> DATA_SOURCE =
      new Field<>("dataSource", self -> {
        final DataSourceConfig dataSourceConfig = self.getDataSourceConfig();
        final HikariConfig config = new HikariConfig();
        config.setDriverClassName(dataSourceConfig.getDriver());
        config.setJdbcUrl(dataSourceConfig.getUrl());
        config.setUsername(dataSourceConfig.getUser());
        config.setPassword(dataSourceConfig.getPassword());
        return new HikariDataSource(config);
      });

  @Override
  default HikariDataSource getDataSource() {
    return get(DATA_SOURCE);
  }
}
