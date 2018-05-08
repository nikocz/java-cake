package pl.nc.javacake.infrastructure.config;

import lombok.Data;
import org.jooq.SQLDialect;
import org.yaml.snakeyaml.Yaml;
import pl.nc.javacake.infrastructure.db.api.DataSourceConfigModule;
import pl.nc.javacake.infrastructure.jooq.api.JooqConfigModule;
import pl.nc.javacake.infrastructure.state.StateComponent;

import java.io.InputStream;

public interface AppConfigComponent extends
    /* impl */ DataSourceConfigModule, JooqConfigModule,
    /* with */ StateComponent
{
  String APP_CONFIG_PATH = "app-config.yml";
  Field<AppConfigComponent, AppConfig> APP_CONFIG =
      new Field<>("appConfig", self -> {
        final Yaml yaml = new Yaml();
        final InputStream appConfigStream = self.getClass().getClassLoader()
            .getResourceAsStream(APP_CONFIG_PATH);
        return yaml.loadAs(appConfigStream, AppConfig.class);
      });

  default AppConfig getAppConfig() {
    return get(APP_CONFIG);
  }

  @Override
  default DataSourceConfig getDataSourceConfig() {
    return getAppConfig().getDataSource();
  }

  @Override
  default JooqConfig getJooqConfig() {
    return getAppConfig().getJooq();
  }

  @Data
  class AppConfig {
    private DataSourceConfigImpl dataSource;
    private JooqConfigImpl jooq;
  }

  @Data
  class DataSourceConfigImpl implements DataSourceConfig {
    private String driver;
    private String url;
    private String user;
    private String password;
  }

  @Data
  class JooqConfigImpl implements JooqConfig {
    private SQLDialect sqlDialect;
  }
}
