package pl.nc.javacake.domain.game;

import java.util.Arrays;
import java.util.List;

interface GameModuleTestData {
  Long GAME_ID = 10L;
  String GAME_NAME = "Some game";
  List<Long> GAME_CATEGORY_IDS = Arrays.asList(11L, 12L, 13L);
}
