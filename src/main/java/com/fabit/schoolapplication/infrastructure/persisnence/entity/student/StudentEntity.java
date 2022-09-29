package com.fabit.schoolapplication.infrastructure.persisnence.entity.student;

import com.fabit.schoolapplication.domain.student.Student;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

@Entity
@Data
@Table(name = "student")
public class StudentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String snils;
  @Column(name = "birth_certificate")
  private String birthCertificate;
  private String passport;

  @AfterDomainEventPublication
  protected void clearDomainEvents() {
    Student.DOMAIN_EVENTS.clear();
  }

  @DomainEvents
  protected Collection<Object> domainEvents() {
    return Collections.unmodifiableList(Student.DOMAIN_EVENTS);
  }

}