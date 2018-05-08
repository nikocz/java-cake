package pl.nc.javacake.domain.game.api;

public interface GameServiceModule {
  GameService getGameService();

  interface GameService {
    void create(final CreateGame game);

    GameInfo findById(final Long id);
  }
}
