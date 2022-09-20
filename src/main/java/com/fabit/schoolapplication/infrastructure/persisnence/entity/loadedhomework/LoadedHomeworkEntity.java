package com.fabit.schoolapplication.infrastructure.persisnence.entity.loadedhomework;

import com.fabit.schoolapplication.domain.loadedhomework.LoadedHomework;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "loaded_homework")
public class LoadedHomeworkEntity {

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
    LoadedHomework.domainEvents.clear();
  }

  @DomainEvents
  protected Collection<Object> domainEvents() {
    return Collections.unmodifiableList(LoadedHomework.domainEvents);
  }


}
