package com.softarch.framework.meta.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ModelBuilderTest {

  private ModelBuilder modelBuilder;

  @BeforeEach
  void setUp() {
    modelBuilder = new ModelBuilder();
  }

  @Test
  void buildModelWithValidPackageName() {
    Model model = modelBuilder.withPackageName("com.softarch.framework.app").build();
    assertNotNull(model);
    assertFalse(model.getMetadataList().isEmpty());
  }

  @Test
  void buildModelWithInvalidPackageName() {
    Model model = modelBuilder.withPackageName("invalid.package.name").build();
    assertNotNull(model);
    assertTrue(model.getMetadataList().isEmpty());
  }

  @Test
  void buildModelWithEmptyPackageName() {
    Model model = modelBuilder.withPackageName("").build();
    assertNotNull(model);
    assertTrue(model.getMetadataList().isEmpty());
  }

  @Test
  void buildModelWithNullPackageName() {
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        modelBuilder.withPackageName(null).build());
    assertEquals("Package name not provided", exception.getMessage());
  }
}