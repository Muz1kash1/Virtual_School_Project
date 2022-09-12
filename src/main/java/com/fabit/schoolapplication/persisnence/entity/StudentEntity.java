package com.fabit.schoolapplication.persisnence.entity;

import com.fabit.schoolapplication.domain.student.Student;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

@Entity
@Data
public class StudentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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