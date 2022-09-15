package com.fabit.schoolapplication.infrastructure.persisnence.entity.homeworkcompletionresult;

import com.fabit.schoolapplication.domain.homeworkcompletionresult.HomeworkCompletionResult;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.lesson.LessonEntity;
import com.sun.istack.NotNull;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "homework_completion_result")
public class HomeworkCompletionResultEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @NotNull
  private TeacherEntity teacher;

  @ManyToOne
  @NotNull
  private StudentEntity student;

  @ManyToOne
  @NotNull
  private LessonEntity lesson;

  @Column(name = "task_completion_result")
  private String taskCompletionResult;

  @AfterDomainEventPublication
  protected void clearDomainEvents() {
    HomeworkCompletionResult.domainEvents.clear();
  }

  @DomainEvents
  protected Collection<Object> domainEvents() {
    return Collections.unmodifiableList(HomeworkCompletionResult.domainEvents);
  }


}
