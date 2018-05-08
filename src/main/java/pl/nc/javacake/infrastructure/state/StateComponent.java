package pl.nc.javacake.infrastructure.state;

import lombok.Value;

import java.util.function.Function;

public interface StateComponent {

  default <I, F> F get(final Field<I, F> field) {
    return State.get(this, field);
  }

  @Value
  class Field<I, F> {
    String name;
    Function<I, F> init;
  }
}
