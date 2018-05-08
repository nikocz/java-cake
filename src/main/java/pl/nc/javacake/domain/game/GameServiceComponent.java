package pl.nc.javacake.domain.game;

import lombok.AllArgsConstructor;
import pl.nc.javacake.domain.category.api.AddGameCategories;
import pl.nc.javacake.domain.category.api.Category;
import pl.nc.javacake.domain.category.api.CategoryServiceModule;
import pl.nc.javacake.domain.game.api.*;
import pl.nc.javacake.infrastructure.state.StateComponent;

import java.util.List;

public interface GameServiceComponent extends
    /* impl */ GameServiceModule,
    /* deps */ GameRepoModule, CategoryServiceModule,
    /* with */ StateComponent
{
  Field<GameServiceComponent, GameServiceImpl> GAME_SERVICE =
      new Field<>("gameService", self ->
          new GameServiceImpl(self.getGameRepo(), self.getCategoryService())
      );

  @Override
  default GameServiceImpl getGameService() {
    return get(GAME_SERVICE);
  }

  @AllArgsConstructor
  class GameServiceImpl implements GameService {
    private final GameRepo gameRepo;
    private final CategoryService categoryService;

    @Override
    public void create(final CreateGame createGame) {
      gameRepo.create(new Game(createGame.getGameId(), createGame.getGameName()));
      categoryService.addGameCats(new AddGameCategories(createGame.getGameId(), createGame.getCategoryIds()));
    }

    @Override
    public GameInfo findById(final Long id) {
      final Game game = gameRepo.findById(id);
      final List<Category> gameCategories = categoryService.findByGameId(id);
      return new GameInfo(game.getId(), game.getName(), gameCategories);
    }
  }
}
