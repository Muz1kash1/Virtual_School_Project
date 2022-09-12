package com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teacher")
@Data
public class TeacherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "standingYears")
    private Integer standingYears;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "passport")
    private String passport;
    @Column(name = "snils")
    private String snils;
}
