package org.softarch.metadriven.app;

import org.softarch.metadriven.spec.annotation.AdditionalGroup;
import org.softarch.metadriven.spec.annotation.EssentialGroup;
import org.softarch.metadriven.spec.annotation.Id;
import org.softarch.metadriven.spec.annotation.Metadata;
import org.softarch.metadriven.spec.annotation.Representative;
import org.softarch.metadriven.spec.annotation.Summary;

@Metadata(name = "Material")
public class Material {


  @Id
  private Long id;
  @Representative
  private String name;
  @Summary
  private Integer quantity;
  @Summary
  private Long price;
  @EssentialGroup(groupName = "manufacture")
  private Long orderId;
  @EssentialGroup(groupName = "manufacture")
  private Long stockId;
  @EssentialGroup(groupName = "manufacture")
  private String manufacturerCode;
  @AdditionalGroup(groupName = "note")
  private String note;

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

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Long getStockId() {
    return stockId;
  }

  public void setStockId(Long stockId) {
    this.stockId = stockId;
  }

  public String getManufacturerCode() {
    return manufacturerCode;
  }

  public void setManufacturerCode(String manufacturerCode) {
    this.manufacturerCode = manufacturerCode;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

}
