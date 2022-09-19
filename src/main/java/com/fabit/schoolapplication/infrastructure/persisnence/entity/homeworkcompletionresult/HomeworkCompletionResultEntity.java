package com.fabit.schoolapplication.infrastructure.persisnence.entity.homeworkcompletionresult;

import com.fabit.schoolapplication.domain.homeworkcompletionresult.HomeworkCompletionResult;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.lesson.HomeworkForClassEntity;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
  @JoinColumn(name = "teacher_id")
  private TeacherEntity teacher;

  @ManyToOne
  @JoinColumn(name = "student_id")
  private StudentEntity student;

  @ManyToOne
  @JoinColumn(name = "lesson_id")
  private HomeworkForClassEntity lesson;

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
