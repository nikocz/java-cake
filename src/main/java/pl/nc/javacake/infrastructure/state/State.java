package pl.nc.javacake.infrastructure.state;

import lombok.Value;
import lombok.experimental.UtilityClass;
import pl.nc.javacake.infrastructure.state.StateComponent.Field;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.synchronizedMap;

@UtilityClass
class State {
  private final Map<InstanceField<?, ?>, Object> instanceStateMap = synchronizedMap(new HashMap<>());

  @SuppressWarnings("unchecked")
  public static <I, F> F get(final Object instance, final Field<I, F> field) {
    return (F) instanceStateMap.computeIfAbsent(
        new InstanceField<>((I) instance, field),
        InstanceField::initVal
    );
  }

  @Value
  private static class InstanceField<I, F> {
    I instance;
    Field<I, F> field;

    public F initVal() {
      return field.getInit().apply(instance);
    }
  }
}
