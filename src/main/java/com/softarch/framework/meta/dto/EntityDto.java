package com.softarch.framework.meta.dto;

import java.util.UUID;

public class EntityDto {
  private UUID id;
  private String entity;
  private String metadataName;

  public EntityDto() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getEntity() {
    return entity;
  }

  public void setEntity(String entity) {
    this.entity = entity;
  }

  public String getMetadataName() {
    return metadataName;
  }

  public void setMetadataName(String metadataName) {
    this.metadataName = metadataName;
  }

}
