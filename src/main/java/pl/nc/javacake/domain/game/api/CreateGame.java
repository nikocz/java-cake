package pl.nc.javacake.domain.game.api;

import lombok.Value;

import java.util.List;

@Value
public class CreateGame {
  Long gameId;
  String gameName;
  List<Long> categoryIds;
}
