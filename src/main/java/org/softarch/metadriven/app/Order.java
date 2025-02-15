package org.softarch.metadriven.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.softarch.metadriven.spec.annotation.AdditionalGroup;
import org.softarch.metadriven.spec.annotation.EssentialGroup;
import org.softarch.metadriven.spec.annotation.Id;
import org.softarch.metadriven.spec.annotation.Metadata;
import org.softarch.metadriven.spec.annotation.State;
import org.softarch.metadriven.spec.annotation.Summary;
import java.util.Calendar;

@Metadata(name = "Order")
public class Order {
  @Id
  private Long id;
  @State(name = "order_state")
  @JsonProperty("order_state")
  private Integer orderState;
  @Summary(name = "order_time")
  @JsonProperty("order_time")
  private Calendar orderTime;
  @Summary
  private Integer total;
  @EssentialGroup(groupName = "sale", name = "customer_id")
  @JsonProperty("customer_id")
  private Long customerId;
  @EssentialGroup(groupName = "sale", name = "store_id")
  @JsonProperty("store_id")
  private Long storeId;
  @AdditionalGroup(groupName = "note", name = "note")
  @JsonProperty("note")
  private String note;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getOrderState() {
    return orderState;
  }

  public void setOrderState(Integer orderState) {
    this.orderState = orderState;
  }

  public Calendar getOrderTime() {
    return orderTime;
  }

  public void setOrderTime(Calendar orderTime) {
    this.orderTime = orderTime;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public Long getStoreId() {
    return storeId;
  }

  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }
}
