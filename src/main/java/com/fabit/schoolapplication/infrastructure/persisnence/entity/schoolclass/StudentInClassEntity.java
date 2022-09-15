package com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Lombok
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
// ---
@Entity
@Table(name = "student_in_class")
public class StudentInClassEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "school_class_id")
  private Long schoolClassId;

  @Column(name = "student_id")
  private Long studentId;

  private StudentInClassEntity(Long schoolClassId, Long studentId) {
    this.schoolClassId = schoolClassId;
    this.studentId = studentId;
  }

  public static StudentInClassEntity of(Long schoolClassId, Long studentId) {
    return new StudentInClassEntity(schoolClassId, studentId);
  }

}
