package com.fabit.schoolapplication.infrastructure.persisnence.entity.lesson;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.lesson.Lesson;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.sun.istack.NotNull;
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

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "lesson")
public class LessonEntity {

  @Id
  @Column(name = "id", nullable = false)
  @NotNull
  private Long id;

  @OneToOne
  @NotNull
  private TeacherEntity teacherId;

  private Discipline discipline;

  private String homeworkTask;

  @AfterDomainEventPublication
  protected void clearDomainEvents() {
    Lesson.domainEvents.clear();
  }

  @DomainEvents
  protected Collection<Object> domainEvents() {
    return Collections.unmodifiableList(Lesson.domainEvents);
  }


}
