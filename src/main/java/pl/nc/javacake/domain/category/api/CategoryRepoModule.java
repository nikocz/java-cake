package pl.nc.javacake.domain.category.api;

import java.util.List;

public interface CategoryRepoModule {
  CategoryRepo getCategoryRepo();

  interface CategoryRepo {
    void create(final Category category);

    List<Category> findAll();

    void addGameCats(final AddGameCategories addGameCategories);

    List<Category> findByGameId(final Long gameId);
  }
}
