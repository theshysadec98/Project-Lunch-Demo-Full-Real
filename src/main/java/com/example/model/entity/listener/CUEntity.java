package com.example.model.entity.listener;

import java.sql.Timestamp;


public interface CUEntity {

  void setModifiedDate(Timestamp modifiedAt);

  void setCreatedDate(Timestamp createdAte);
}
