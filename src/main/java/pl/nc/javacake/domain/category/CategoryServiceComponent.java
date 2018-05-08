package pl.nc.javacake.domain.category;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;
import pl.nc.javacake.domain.category.api.CategoryRepoModule;
import pl.nc.javacake.domain.category.api.CategoryServiceModule;
import pl.nc.javacake.infrastructure.state.StateComponent;

public interface CategoryServiceComponent extends
    /* impl */ CategoryServiceModule,
    /* deps */ CategoryRepoModule,
    /* with */ StateComponent
{
  Field<CategoryServiceComponent, CategoryServiceImpl> CATEGORY_SERVICE =
      new Field<>("categoryService", self ->
          new CategoryServiceImpl(self.getCategoryRepo())
      );

  @Override
  default CategoryServiceImpl getCategoryService() {
    return get(CATEGORY_SERVICE);
  }

  @AllArgsConstructor
  class CategoryServiceImpl implements CategoryService {

    @Delegate
    private final CategoryRepo categoryRepo;
  }
}
