package pl.nc.javacake.domain.game;

import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import pl.nc.javacake.domain.game.api.Game;
import pl.nc.javacake.domain.game.api.GameRepoModule;
import pl.nc.javacake.infrastructure.jooq.api.JooqModule;
import pl.nc.javacake.infrastructure.state.StateComponent;

import static pl.nc.javacake.jooq.Tables.GAME;

public interface JooqGameRepoComponent extends
    /* impl */ GameRepoModule,
    /* deps */ JooqModule,
    /* with */ StateComponent
{
  Field<JooqGameRepoComponent, JooqGameRepo> GAME_REPO =
      new Field<>("gameRepo", self ->
          new JooqGameRepo(self.getDslContext())
      );

  @Override
  default JooqGameRepo getGameRepo() {
    return get(GAME_REPO);
  }

  @AllArgsConstructor
  class JooqGameRepo implements GameRepo {
    private final DSLContext dsl;

    @Override
    public void create(final Game game) {
      dsl.insertInto(GAME)
          .set(GAME.ID, game.getId())
          .set(GAME.NAME, game.getName())
          .execute();
    }

    @Override
    public Game findById(final Long id) {
      return dsl.selectFrom(GAME)
          .where(GAME.ID.eq(id))
          .fetchOne(record -> new Game(record.getId(), record.getName()));
    }
  }
}
