package pl.nc.javacake.domain.game;

import org.junit.jupiter.api.Test;
import pl.nc.javacake.domain.game.api.CreateGame;
import pl.nc.javacake.infrastructure.extension.CleanTablesExtension;
import pl.nc.javacake.infrastructure.jooq.TestJooqComponent;
import pl.nc.javacake.jooq.tables.records.GameRecord;

import java.util.stream.LongStream;

import static info.solidsoft.mockito.java8.AssertionMatcher.assertArg;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static pl.nc.javacake.domain.game.GameModuleTestData.*;
import static pl.nc.javacake.jooq.Tables.GAME;

class GameModuleTest implements
    GameServiceComponent,
    JooqGameRepoComponent,
    TestJooqComponent,
    CleanTablesExtension
{
  private final CategoryService categoryService = mock(CategoryService.class);

  @Override
  public CategoryService getCategoryService() {
    return categoryService;
  }

  @Test
  public void shouldCreateGame() {
    //given
    final CreateGame createGame = new CreateGame(GAME_ID, GAME_NAME, GAME_CATEGORY_IDS);

    //when
    getGameService().create(createGame);

    //then
    final GameRecord gameRecord = getDslContext()
        .selectFrom(GAME)
        .where(GAME.ID.eq(createGame.getGameId()))
        .fetchOne();

    assertThat(gameRecord.getId())
        .isEqualTo(GAME_ID);
    assertThat(gameRecord.getName())
        .isEqualTo(GAME_NAME);

    verify(categoryService).addGameCats(assertArg(addGameCategories -> {
      assertThat(addGameCategories.getGameId()).isEqualTo(GAME_ID);
      assertThat(addGameCategories.getCategoryIds()).isEqualTo(GAME_CATEGORY_IDS);
    }));
  }

  private static LongStream shouldCreateCategory() {
    return LongStream.rangeClosed(1L, 10L);
  }
}
