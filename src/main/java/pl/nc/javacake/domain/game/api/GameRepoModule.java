package pl.nc.javacake.domain.game.api;

public interface GameRepoModule {
  GameRepo getGameRepo();

  interface GameRepo {
    void create(final Game game);

    Game findById(final Long id);
  }
}
