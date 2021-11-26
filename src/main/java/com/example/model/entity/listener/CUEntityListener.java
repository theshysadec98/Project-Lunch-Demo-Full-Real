package com.example.model.entity.listener;

import java.sql.Timestamp;
import java.util.Calendar;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * @author akitoshi
 */
public class CUEntityListener {

  @PrePersist
  public void onCreate(Object entity) {
    if (entity instanceof CUEntity) {
      Timestamp currentTimestamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
      CUEntity cuEntity = (CUEntity) entity;
      cuEntity.setCreatedDate(currentTimestamp);
      cuEntity.setModifiedDate(currentTimestamp);
    }
  }

  @PreUpdate
  public void onUpdate(Object entity) {
    if (entity instanceof CUEntity) {
      CUEntity cuEntity = (CUEntity) entity;
      cuEntity.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
    }
  }

}
