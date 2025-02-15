package org.softarch.metadriven.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Metadata {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", nullable = false)
  private Long id;

  private String name;

  private Date timestamp;
  private int type;
  private String baseClass;
  @ManyToMany
  private List<Attribute> idList;
  @ManyToMany
  private List<Attribute> representativeList;
  @ManyToMany
  private List<Attribute> stateList;
  @ManyToMany
  private List<Attribute> summaryList;
  @ManyToMany
  private List<AttributeGroup> essentialGroupList;
  @ManyToMany
  private List<AttributeGroup> additionalGroupList;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getBaseClass() {
    return baseClass;
  }

  public void setBaseClass(String baseClass) {
    this.baseClass = baseClass;
  }

  public List<Attribute> getIdList() {
    return idList;
  }

  public void setIdList(List<Attribute> idList) {
    this.idList = idList;
  }

  public List<Attribute> getRepresentativeList() {
    return representativeList;
  }

  public void setRepresentativeList(List<Attribute> representativeList) {
    this.representativeList = representativeList;
  }

  public List<Attribute> getStateList() {
    return stateList;
  }

  public void setStateList(List<Attribute> stateList) {
    this.stateList = stateList;
  }

  public List<Attribute> getSummaryList() {
    return summaryList;
  }

  public void setSummaryList(List<Attribute> summaryList) {
    this.summaryList = summaryList;
  }

  public List<AttributeGroup> getEssentialGroupList() {
    return essentialGroupList;
  }

  public void setEssentialGroupList(
      List<AttributeGroup> essentialGroupList) {
    this.essentialGroupList = essentialGroupList;
  }

  public List<AttributeGroup> getAdditionalGroupList() {
    return additionalGroupList;
  }

  public void setAdditionalGroupList(
      List<AttributeGroup> additionalGroupList) {
    this.additionalGroupList = additionalGroupList;
  }
}
