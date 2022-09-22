package com.fabit.schoolapplication.infrastructure.persisnence.entity.homeworkforclass;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClass;
import java.time.LocalDate;
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

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "homework_for_class")
public class HomeworkForClassEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "discipline")
  private Discipline discipline;
  @Column(name = "homework_task")
  private String homeworkTask;
  @Column(name = "date")
  private LocalDate date;
  @Column(name = "school_class_entity_id")
  Long schoolClassId;

  @AfterDomainEventPublication
  protected void clearDomainEvents() {
    HomeworkForClass.DOMAIN_EVENTS.clear();
  }

  @DomainEvents
  protected Collection<Object> domainEvents() {
    return Collections.unmodifiableList(HomeworkForClass.DOMAIN_EVENTS);
  }


}
