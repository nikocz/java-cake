package pl.nc.javacake.infrastructure.db.api;

public interface DataSourceConfigModule {
  DataSourceConfig getDataSourceConfig();

  interface DataSourceConfig {
    String getDriver();

    String getUrl();

    String getUser();

    String getPassword();
  }
}
