package com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
@Data
public class TeacherEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "standingYears")
  private Integer standingYears;

  @Column(name = "fullName")
  private String fullName;

  @Column(name = "passport")
  private String passport;

  @Column(name = "snils")
  private String snils;
}
