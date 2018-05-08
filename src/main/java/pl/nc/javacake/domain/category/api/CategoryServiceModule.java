package pl.nc.javacake.domain.category.api;

import java.util.List;

public interface CategoryServiceModule {

  CategoryService getCategoryService();

  interface CategoryService {
    void create(final Category category);

    List<Category> findAll();

    void addGameCats(final AddGameCategories addGameCategories);

    List<Category> findByGameId(final Long gameId);
  }
}
