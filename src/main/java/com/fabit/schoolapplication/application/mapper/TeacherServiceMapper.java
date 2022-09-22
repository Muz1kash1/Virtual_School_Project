package com.fabit.schoolapplication.application.mapper;

import com.fabit.schoolapplication.domain.FullName;
import com.fabit.schoolapplication.domain.RussianPassport;
import com.fabit.schoolapplication.domain.Snils;
import com.fabit.schoolapplication.domain.teacher.Teacher;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.teacher.dto.FullNameDto;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.teacher.dto.PassportDto;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.teacher.dto.SnilsDto;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.teacher.dto.TeacherDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceMapper {

  /**
   * Маппинг ДТО учителя в доменную модель.
   *
   * @param teacherDto - учитель
   * @return Teacher
   */
  public Teacher mapDtoToDomain(TeacherDto teacherDto) {
    return Teacher.of(
        TeacherId.of(1L),
        mapDtoToDomain(teacherDto.getFullName()),
        mapDtoToDomain(teacherDto.getPassport()),
        mapDtoToDomain(teacherDto.getSnils()),
        teacherDto.getStandingYears(),
        teacherDto.isActive());
  }


  private RussianPassport mapDtoToDomain(PassportDto passportDto) {
    return RussianPassport.of(
        passportDto.getSerial(),
        passportDto.getNumber(),
        passportDto.getBirthday()
    );
  }

  private FullName mapDtoToDomain(FullNameDto fullNameDto) {
    return FullName.of(
        fullNameDto.getName(), fullNameDto.getSurname(), fullNameDto.getPatronymic()
    );
  }

  private Snils mapDtoToDomain(SnilsDto snilsDto) {
    return Snils.of(snilsDto.getNumberView());
  }

  /**
   * Маппинг доменной модели учителя в persistent сущность.
   *
   * @param teacher - учитель
   * @return TeacherEntity
   */
  public TeacherEntity mapDomainToEntity(Teacher teacher) {

    TeacherEntity teacherEntity = new TeacherEntity();

    teacherEntity.setPassport(teacher.getPassport().toString());
    teacherEntity.setSnils(teacher.getSnils().toString());
    teacherEntity.setActive(teacher.isActive());
    teacherEntity.setStandingYears(teacher.getStandingYears());
    teacherEntity.setFullName(
        teacher.getFullName().getName()
            + " " + teacher.getFullName().getSurname()
            + " " + teacher.getFullName().getPatronymic()
    );

    return teacherEntity;
  }

  /**
   * Маппинг persistent сущности в доменную модель (учитель).
   *
   * @param teacherEntity - учитель
   * @return Teacher
   */
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

  /**
   * Маппинг persistent сущности в доменную модель (паспорт).
   *
   * @param passport - строка паспорта
   * @return RussianPassport
   */
  public RussianPassport mapEntityPassportToDomain(String passport) {
    String[] arr = passport.split(" ");
    LocalDate birthday = LocalDate.parse(arr[2]);
    return RussianPassport.of(arr[0], arr[1], birthday);
  }

  public Snils mapEntitySnilsToDomain(String snils) {
    return Snils.of(snils);
  }
}
