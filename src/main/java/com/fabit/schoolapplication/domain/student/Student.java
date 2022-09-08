package com.fabit.schoolapplication.domain.student;

import com.fabit.schoolapplication.domain.Passport;
import com.fabit.schoolapplication.domain.Snils;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Student {
    private long id;
    private String name;
    private Snils snils;
    private BirthCertificate birthCertificate;
    private Passport passport;
    private LocalDate birthday;

    private Student(long id, String name, Snils snils, BirthCertificate birthCertificate, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.snils = snils;
        this.birthCertificate = birthCertificate;
        this.birthday = birthday;
    }

    private Student(long id, String name, Snils snils, Passport passport, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.snils = snils;
        if (LocalDate.now().getYear() - birthday.getYear() >= 14) {
            this.passport = passport;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static Student create(long id, String name, Snils snils, BirthCertificate birthCertificate, LocalDate birthday) {
        return new Student(id, name, snils, birthCertificate, birthday);
    }

    public static Student create(long id, String name, Snils snils, Passport passport, LocalDate birthday) {
        return new Student(id, name, snils, passport, birthday);
    }

    public void changeSnils(Snils snils) {
        this.snils = snils;
    }

    public void changeBirthCertificate(BirthCertificate birthCertificate) {
        this.birthCertificate = birthCertificate;
    }

    public void addPassport(Passport passport) {
        this.passport = passport;
    }
}