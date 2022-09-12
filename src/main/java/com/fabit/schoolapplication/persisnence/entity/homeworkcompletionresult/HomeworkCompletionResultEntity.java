package com.fabit.schoolapplication.persisnence.entity.homeworkcompletionresult;

import com.fabit.schoolapplication.persisnence.entity.StudentEntity;
import com.fabit.schoolapplication.persisnence.entity.lesson.LessonEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader.Strategy;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "homework_completion_result")
public class HomeworkCompletionResultEntity {

  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  @OneToOne
  private TeacherEntity teacherId;

  @OneToOne
  private StudentEntity student;

  @OneToOne
  private LessonEntity lesson;

  private String taskCompletionResult;



}
