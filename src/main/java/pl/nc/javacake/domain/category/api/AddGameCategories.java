package pl.nc.javacake.domain.category.api;

import lombok.Value;

import java.util.List;

@Value
public class AddGameCategories {
  Long gameId;
  List<Long> categoryIds;
}
