package pl.nc.javacake.domain.game.api;

import lombok.Value;
import pl.nc.javacake.domain.category.api.Category;

import java.util.List;

@Value
public class GameInfo {
  Long id;
  String name;
  List<Category> categories;
}
