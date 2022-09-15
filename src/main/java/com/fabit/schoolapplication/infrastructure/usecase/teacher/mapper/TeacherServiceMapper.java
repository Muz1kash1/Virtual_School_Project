package com.fabit.schoolapplication.infrastructure.usecase.teacher.mapper;

import com.fabit.schoolapplication.domain.FullName;
import com.fabit.schoolapplication.domain.Passport;
import com.fabit.schoolapplication.domain.Snils;
import com.fabit.schoolapplication.domain.teacher.Teacher;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import com.fabit.schoolapplication.infrastructure.controller.teacher.dto.FullNameDto;
import com.fabit.schoolapplication.infrastructure.controller.teacher.dto.PassportDto;
import com.fabit.schoolapplication.infrastructure.controller.teacher.dto.SnilsDto;
import com.fabit.schoolapplication.infrastructure.controller.teacher.dto.TeacherDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceMapper {
  public Teacher mapDtoToDomain(TeacherDto teacherDto) {
    return Teacher.of(
        TeacherId.of(1L),
        mapDtoToDomain(teacherDto.getFullName()),
        mapDtoToDomain(teacherDto.getPassport()),
        mapDtoToDomain(teacherDto.getSnils()),
        teacherDto.getStandingYears(),
        teacherDto.isActive());
  }

  private Passport mapDtoToDomain(PassportDto passportDto) {
    return Passport.of(passportDto.getSerial(), passportDto.getNumber());
  }

  private FullName mapDtoToDomain(FullNameDto fullNameDto) {
    return FullName.of(
        fullNameDto.getName(), fullNameDto.getSurname(), fullNameDto.getPatronymic());
  }

  private Snils mapDtoToDomain(SnilsDto snilsDto) {
    return Snils.of(snilsDto.getNumberView());
  }

  public TeacherEntity mapDomainToEntity(Teacher teacher) {
    TeacherEntity teacherEntity = new TeacherEntity();
    teacherEntity.setPassport(teacher.getPassport().toString());
    teacherEntity.setSnils(teacher.getSnils().toString());
    teacherEntity.setActive(teacher.isActive());
    teacherEntity.setFullName(
        teacher.getFullName().getName()
            + " "
            + teacher.getFullName().getSurname()
            + " "
            + teacher.getFullName().getPatronymic());
    teacherEntity.setStandingYears(teacher.getStandingYears());
    return teacherEntity;
  }

  public Teacher mapEntityToDomain(TeacherEntity teacherEntity) {
    return Teacher.of(
        TeacherId.of(teacherEntity.getId()),
        mapEntityFullNameToDomain(teacherEntity.getFullName()),
        mapEntityPassportToDomain(teacherEntity.getPassport()),
        mapEntitySnilsToDomain(teacherEntity.getSnils()),
        teacherEntity.getStandingYears(),
        teacherEntity.isActive());
  }

  public FullName mapEntityFullNameToDomain(String fullName) {
    String[] arr = fullName.split(" ");
    return FullName.of(arr[0], arr[1], arr[2]);
  }

  public Passport mapEntityPassportToDomain(String passport) {
    String[] arr = passport.split(" ");
    return Passport.of(arr[0], arr[1]);
  }

  public Snils mapEntitySnilsToDomain(String snils) {
    return Snils.of(snils);
  }
}
