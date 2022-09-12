package com.fabit.schoolapplication.persisnence.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class StudentEntity {
  @Id
  @Column(name = "id", nullable = false)
  private Long id;
  private String name;
  private String snils;
  private String birthCertificate;
  private String passport;
  private LocalDate birthday;
}
