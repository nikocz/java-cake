package pl.nc.javacake.domain.category;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pl.nc.javacake.domain.category.api.Category;
import pl.nc.javacake.infrastructure.extension.CleanTablesExtension;
import pl.nc.javacake.infrastructure.jooq.TestJooqComponent;
import pl.nc.javacake.jooq.tables.records.CategoryRecord;

import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.nc.javacake.domain.category.CategoryModuleTestData.CATEGORY_NAME;
import static pl.nc.javacake.jooq.Tables.CATEGORY;

class CategoryModuleTest implements
    CategoryServiceComponent,
    JooqCategoryRepoComponent,
    TestJooqComponent,
    CleanTablesExtension
{

  @ParameterizedTest
  @MethodSource
  public void shouldCreateCategory(final Long categoryId) {
    //given
    final Category category = new Category(categoryId, CATEGORY_NAME);

    //when
    getCategoryService().create(category);

    //then
    final CategoryRecord categoryRecord = getDslContext()
        .selectFrom(CATEGORY)
        .where(CATEGORY.ID.eq(categoryId))
        .fetchOne();

    assertThat(categoryRecord.getId())
        .isEqualTo(categoryId);
    assertThat(categoryRecord.getName())
        .isEqualTo(CATEGORY_NAME);
  }

  private static LongStream shouldCreateCategory() {
    return LongStream.rangeClosed(10L, 1000L);
  }
}
