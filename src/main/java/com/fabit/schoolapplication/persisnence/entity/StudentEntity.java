package com.fabit.schoolapplication.persisnence.entity;

import com.fabit.schoolapplication.domain.student.Student;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

@Entity
@Data
public class StudentEntity {
  @Id
  @Column(name = "id", nullable = false)
  private Long id;
  private String name;
  private String snils;
  private String birthCertificate;
  private String passport;
  private LocalDate birthday;

  @AfterDomainEventPublication
  protected void clearDomainEvents() {
    Student.domainEvents.clear();
  }

  @DomainEvents
  protected Collection<Object> domainEvents() {
    return Collections.unmodifiableList(Student.domainEvents);
  }
}