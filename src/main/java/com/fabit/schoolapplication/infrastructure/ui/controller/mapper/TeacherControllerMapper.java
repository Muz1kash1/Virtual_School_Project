package com.fabit.schoolapplication.infrastructure.ui.controller.mapper;

import com.fabit.schoolapplication.domain.generalvalueobject.fullname.FullName;
import com.fabit.schoolapplication.domain.generalvalueobject.passportvo.impl.RussianPassport;
import com.fabit.schoolapplication.domain.generalvalueobject.snils.Snils;
import com.fabit.schoolapplication.domain.teacher.Teacher;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import com.fabit.schoolapplication.infrastructure.ui.controller.teacher.dto.FullNameDto;
import com.fabit.schoolapplication.infrastructure.ui.controller.teacher.dto.PassportDto;
import com.fabit.schoolapplication.infrastructure.ui.controller.teacher.dto.SnilsDto;
import com.fabit.schoolapplication.infrastructure.ui.controller.teacher.dto.TeacherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Clock;

@Service
@RequiredArgsConstructor
public class TeacherControllerMapper {
  private final TeacherRepository teacherRepository;

  /**
   * Маппинг ДТО учителя в доменную модель.
   *
   * @param teacherDto - учитель
   * @return Teacher
   */
  public Teacher mapDtoToDomain(TeacherDto teacherDto) {
    return Teacher.of(
      TeacherId.of(
        teacherRepository.getNextId()
      ),
      mapDtoToDomain(teacherDto.getFullName()),
      mapDtoToDomain(teacherDto.getPassport()),
      mapDtoToDomain(teacherDto.getSnils())
    );
  }

  private RussianPassport mapDtoToDomain(PassportDto passportDto) {
    return RussianPassport.of(
      passportDto.getSerial(), passportDto.getNumber(), passportDto.getBirthday(),
      Clock.systemUTC()
    );
  }

  private FullName mapDtoToDomain(FullNameDto fullNameDto) {
    return FullName.of(
      fullNameDto.getName(), fullNameDto.getSurname(), fullNameDto.getPatronymic());
  }

  private Snils mapDtoToDomain(SnilsDto snilsDto) {
    return Snils.of(snilsDto.getNumberView());
  }

}
