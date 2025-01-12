package com.softarch.framework.meta.assembly;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped

public class EntityMapProvider {
  private final Map<UUID, Object> entityMap = Collections.synchronizedMap(new HashMap<>());
  @Produces
  public Map<UUID, Object> entityMap(InjectionPoint injectionPoint) {
    return entityMap;
  }

}
