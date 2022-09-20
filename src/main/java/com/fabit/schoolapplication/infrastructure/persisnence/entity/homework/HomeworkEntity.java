package com.fabit.schoolapplication.infrastructure.persisnence.entity.homework;

import com.fabit.schoolapplication.domain.homework.Homework;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.homeworkforclass.HomeworkForClassEntity;
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
@Table(name = "homework")
public class HomeworkEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "student_id")
  private Long student;
  @Column(name = "homework_for_class_id")
  private Long homeworkForClassId;
  @Column(name = "task_completion_result")
  private String taskCompletionResult;

  @AfterDomainEventPublication
  protected void clearDomainEvents() {
    Homework.domainEvents.clear();
  }

  @DomainEvents
  protected Collection<Object> domainEvents() {
    return Collections.unmodifiableList(Homework.domainEvents);
  }


}
