package com.example.model.entity.listener;

import java.sql.Timestamp;

/**
 * @author akitoshi
 */
public interface CUEntity {

  void setModifiedDate(Timestamp modifiedAt);

  void setCreatedDate(Timestamp createdAte);
}
