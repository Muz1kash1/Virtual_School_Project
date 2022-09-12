package com.fabit.schoolapplication.infrastructure.persisnence.entity.homeworkcompletionresult;

import com.fabit.schoolapplication.domain.homeworkcompletionresult.HomeworkCompletionResult;
import com.fabit.schoolapplication.persisnence.entity.StudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.lesson.LessonEntity;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
  @Column(name = "id", nullable = false)
  private Long id;

  @OneToOne
  private TeacherEntity teacherId;

  @OneToOne
  private StudentEntity student;

  @OneToOne
  private LessonEntity lesson;

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
