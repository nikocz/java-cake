package pl.nc.javacake.domain.category;

import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import pl.nc.javacake.domain.category.api.AddGameCategories;
import pl.nc.javacake.domain.category.api.Category;
import pl.nc.javacake.domain.category.api.CategoryRepoModule;
import pl.nc.javacake.infrastructure.jooq.api.JooqModule;
import pl.nc.javacake.infrastructure.state.StateComponent;
import pl.nc.javacake.jooq.tables.records.GameCategoryRecord;

import java.util.List;
import java.util.stream.Collectors;

import static pl.nc.javacake.jooq.Tables.CATEGORY;
import static pl.nc.javacake.jooq.Tables.GAME_CATEGORY;

public interface JooqCategoryRepoComponent extends
    /* impl */ CategoryRepoModule,
    /* deps */ JooqModule,
    /* with */ StateComponent
{
  Field<JooqCategoryRepoComponent, JooqCategoryRepo> CATEGORY_REPO =
      new Field<>("categoryRepo", self ->
          new JooqCategoryRepo(self.getDslContext())
      );

  @Override
  default CategoryRepo getCategoryRepo() {
    return get(CATEGORY_REPO);
  }

  @AllArgsConstructor
  class JooqCategoryRepo implements CategoryRepo {
    private final DSLContext dsl;

    @Override
    public void create(final Category category) {
      dsl.insertInto(CATEGORY)
          .set(CATEGORY.ID, category.getId())
          .set(CATEGORY.NAME, category.getName())
          .execute();
    }

    @Override
    public List<Category> findAll() {
      return dsl.selectFrom(CATEGORY)
          .fetch(this::mapCategory);
    }

    @Override
    public void addGameCats(final AddGameCategories addGameCategories) {
      final Long gameId = addGameCategories.getGameId();
      final List<GameCategoryRecord> gameCategories = addGameCategories.getCategoryIds()
          .stream()
          .map(categoryId -> new GameCategoryRecord(gameId, categoryId))
          .collect(Collectors.toList());
      dsl.batchInsert(gameCategories)
          .execute();
    }

    @Override
    public List<Category> findByGameId(final Long gameId) {
      return dsl.select(CATEGORY.fields())
          .from(CATEGORY)
          .join(GAME_CATEGORY).onKey()
          .where(GAME_CATEGORY.GAME_ID.eq(gameId))
          .fetch(this::mapCategory);
    }

    private Category mapCategory(final Record record) {
      return new Category(record.get(CATEGORY.ID), record.get(CATEGORY.NAME));
    }
  }
}
