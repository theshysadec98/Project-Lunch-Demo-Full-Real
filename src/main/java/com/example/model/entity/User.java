package com.example.model.entity;

import com.example.model.entity.listener.CUEntity;
import com.example.model.entity.listener.CUEntityListener;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@EntityListeners(CUEntityListener.class)
@Table(name = "users")
public class User implements CUEntity, Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, updatable = false)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "email")
  private String email;

  @Column(name = "phone")
  private String phone;

  @Column(name = "address")
  private String address;

  @Column(name = "dateofbirth")
  private LocalDate dateOfBirth;

  @Column(name = "created_date")
  private Timestamp createdDate;

  @Column(name = "modified_date")
  private Timestamp modifiedDate;

  private List<Booking> bookingList;
}
