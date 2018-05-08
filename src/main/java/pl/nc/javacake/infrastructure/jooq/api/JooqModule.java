package pl.nc.javacake.infrastructure.jooq.api;

import org.jooq.DSLContext;

public interface JooqModule {
  DSLContext getDslContext();
}
