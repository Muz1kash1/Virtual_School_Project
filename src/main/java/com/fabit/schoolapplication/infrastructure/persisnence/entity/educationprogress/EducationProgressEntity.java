package com.fabit.schoolapplication.infrastructure.persisnence.entity.educationprogress;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "education_progress")
public class EducationProgressEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long lessonId;
  private Long teacherId;
  private Long studentId;
  private LocalDateTime whenCreated;

  @OneToMany(mappedBy = "educationProgress")
  private List<MarkEntity> marks = new ArrayList<>(2);
}
