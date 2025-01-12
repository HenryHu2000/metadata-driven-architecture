package com.softarch.framework.meta.model;

import static org.junit.jupiter.api.Assertions.*;

import com.softarch.framework.app.Order;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

@QuarkusTest
public class MetadataBuilderTest {
  private MetadataBuilder metadataBuilder;

  @BeforeEach
  void setUp() {
    metadataBuilder = new MetadataBuilder();
  }

  @Test
  public void buildMetadataWithValidClass() {
    Metadata metadata = metadataBuilder.withClass(Order.class).build();
    assertEquals(1, metadata.getIdList().size());
    assertEquals(1, metadata.getStateList().size());
    assertEquals(2, metadata.getSummaryList().size());
    assertEquals(1, metadata.getEssentialGroupList().size());
    assertEquals(1, metadata.getAdditionalGroupList().size());
    assertEquals(2, metadata.getEssentialGroupList().get(0).getAttributes().size());
    assertEquals(1, metadata.getAdditionalGroupList().get(0).getAttributes().size());
  }

  @Test
  public void buildMetadataWithNoAnnotations() {
    Metadata metadata = metadataBuilder.withClass(Object.class).build();
    assertTrue(metadata.getIdList().isEmpty());
    assertTrue(metadata.getStateList().isEmpty());
    assertTrue(metadata.getSummaryList().isEmpty());
    assertTrue(metadata.getEssentialGroupList().isEmpty());
    assertTrue(metadata.getAdditionalGroupList().isEmpty());
  }

  @Test
  public void buildMetadataWithoutClass() {
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
      new MetadataBuilder().build());
    assertEquals("Class not provided", exception.getMessage());
  }

}
