package org.softarch.metadriven.spec.parser;

import org.softarch.metadriven.model.entity.Attribute;
import org.softarch.metadriven.model.entity.AttributeGroup;
import org.softarch.metadriven.model.entity.AttributeType;
import org.softarch.metadriven.model.entity.Metadata;
import org.softarch.metadriven.spec.annotation.AdditionalGroup;
import org.softarch.metadriven.spec.annotation.EssentialGroup;
import org.softarch.metadriven.spec.annotation.Id;
import org.softarch.metadriven.spec.annotation.Representative;
import org.softarch.metadriven.spec.annotation.State;
import org.softarch.metadriven.spec.annotation.Summary;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class MetadataBuilder {
  private Class<?> clazz;

  public MetadataBuilder withClass(Class<?> clazz) {
    this.clazz = clazz;
    return this;
  }

  public Metadata build() {
    if (clazz == null) {
      throw new IllegalArgumentException("Class not provided");
    }
    Metadata metadata = new Metadata();
    if (clazz.isAnnotationPresent(org.softarch.metadriven.spec.annotation.Metadata.class)
        && !clazz.getAnnotation(org.softarch.metadriven.spec.annotation.Metadata.class).name().isEmpty()) {
      metadata.setName(clazz.getAnnotation(
          org.softarch.metadriven.spec.annotation.Metadata.class).name());
    } else {
      metadata.setName(clazz.getName());
    }
    metadata.setBaseClass(clazz.getName());
    metadata.setIdList(new ArrayList<>());
    metadata.setStateList(new ArrayList<>());
    metadata.setRepresentativeList(new ArrayList<>());
    metadata.setSummaryList(new ArrayList<>());
    metadata.setEssentialGroupList(new ArrayList<>());
    metadata.setAdditionalGroupList(new ArrayList<>());

    for (Field field : clazz.getDeclaredFields()) {
      if (field.isAnnotationPresent(Id.class)) {
        metadata.getIdList().add(buildAttribute(field, field.getAnnotation(Id.class).name()));
      } else if (field.isAnnotationPresent(State.class)) {
        metadata.getStateList().add(buildAttribute(field, field.getAnnotation(State.class).name()));
      } else if (field.isAnnotationPresent(Representative.class)) {
        metadata.getRepresentativeList().add(buildAttribute(field, field.getAnnotation(Representative.class).name()));
      } else if (field.isAnnotationPresent(Summary.class)) {
        metadata.getSummaryList().add(buildAttribute(field, field.getAnnotation(Summary.class).name()));
      } else if (field.isAnnotationPresent(EssentialGroup.class)) {
        String groupName = field.getAnnotation(EssentialGroup.class).groupName();
        AttributeGroup attrGroup = metadata.getEssentialGroupList().stream()
            .filter(group -> group.getName().equals(groupName))
            .findAny().orElseGet(() -> {
              AttributeGroup newGroup = new AttributeGroup();
              newGroup.setName(groupName);
              newGroup.setAttributes(new ArrayList<>());
              metadata.getEssentialGroupList().add(newGroup);
              return newGroup;
            });
        attrGroup.getAttributes().add(buildAttribute(field, field.getAnnotation(EssentialGroup.class).name()));
      } else if (field.isAnnotationPresent(AdditionalGroup.class)) {
        String groupName = field.getAnnotation(AdditionalGroup.class).groupName();
        AttributeGroup attrGroup = metadata.getAdditionalGroupList().stream()
            .filter(group -> group.getName().equals(groupName))
            .findAny().orElseGet(() -> {
              AttributeGroup newGroup = new AttributeGroup();
              newGroup.setName(groupName);
              newGroup.setAttributes(new ArrayList<>());
              metadata.getAdditionalGroupList().add(newGroup);
              return newGroup;
            });
        attrGroup.getAttributes().add(buildAttribute(field, field.getAnnotation(AdditionalGroup.class).name()));
      }

    }
    return metadata;
  }

  private Attribute buildAttribute(Field field, String name) {
    Attribute attribute = new Attribute();
    if (!name.isEmpty()) {
      attribute.setName(name);
    } else {
      attribute.setName(field.getName());
    }
    switch (field.getType().descriptorString()) {
      case "B":
      case "Ljava/lang/Byte;":
        attribute.setType(AttributeType.BYTE);
        break;
      case "S":
      case "Ljava/lang/Short;":
        attribute.setType(AttributeType.SHORT);
        break;
      case "I":
      case "Ljava/lang/Integer;":
        attribute.setType(AttributeType.INT);
        break;
      case "J":
      case "Ljava/lang/Long;":
        attribute.setType(AttributeType.LONG);
        break;
      case "F":
      case "Ljava/lang/Float;":
        attribute.setType(AttributeType.FLOAT);
        break;
      case "D":
      case "Ljava/lang/Double;":
        attribute.setType(AttributeType.DOUBLE);
        break;
      case "Z":
      case "Ljava/lang/Boolean;":
        attribute.setType(AttributeType.BOOLEAN);
        break;
      case "C":
      case "Ljava/lang/Character;":
        attribute.setType(AttributeType.CHAR);
        break;
      case "Ljava/lang/String;":
        attribute.setType(AttributeType.STRING);
        break;
      case "Ljava/util/Calendar;":
        attribute.setType(AttributeType.TIMESTAMP);
        break;
      default:
        throw new IllegalArgumentException("Unrecognized field type");
    }
    return attribute;
  }
}
