package pl.nc.javacake.app;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import pl.nc.javacake.domain.category.CategoryServiceComponent;
import pl.nc.javacake.domain.category.JooqCategoryRepoComponent;
import pl.nc.javacake.domain.game.GameServiceComponent;
import pl.nc.javacake.domain.game.JooqGameRepoComponent;
import pl.nc.javacake.domain.game.api.GameInfo;
import pl.nc.javacake.infrastructure.config.AppConfigComponent;
import pl.nc.javacake.infrastructure.db.DataSourceComponent;
import pl.nc.javacake.infrastructure.jooq.JooqComponent;
import spark.Spark;

@Slf4j
public class App implements
    CategoryServiceComponent, JooqCategoryRepoComponent,
    GameServiceComponent, JooqGameRepoComponent,
    JooqComponent, DataSourceComponent,
    AppConfigComponent
{

  public static void main(final String[] args) {
    final App app = new App();
    final Gson gson = new Gson();

    Spark.get("/game/:id", (req, res) -> {
      final Long gameId = Long.valueOf(req.params(":id"));
      log.info("Find game [id = {}]", gameId);
      final GameInfo game = app.getGameService().findById(gameId);
      res.type("application/json");
      return gson.toJson(game);
    });
  }
}
