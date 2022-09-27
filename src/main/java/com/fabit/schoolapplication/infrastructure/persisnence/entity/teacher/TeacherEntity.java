package com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher;

import com.fabit.schoolapplication.domain.teacher.Teacher;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

@Entity
@Table(name = "teacher")
@Data
public class TeacherEntity {

  @Id
  private Long id;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "passport")
  private String passport;

  @Column(name = "snils")
  private String snils;

  @Column(name = "is_active")
  private boolean isActive;

  @AfterDomainEventPublication
  protected void clearDomainEvents() {
    Teacher.DOMAIN_EVENTS.clear();
  }

  @DomainEvents
  protected Collection<Object> domainEvents() {
    return Collections.unmodifiableList(Teacher.DOMAIN_EVENTS);
  }

}
