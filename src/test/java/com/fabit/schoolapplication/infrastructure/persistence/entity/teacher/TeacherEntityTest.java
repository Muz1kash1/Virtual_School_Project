package com.fabit.schoolapplication.infrastructure.persistence.entity.teacher;

import com.fabit.schoolapplication.domain.FullName;
import com.fabit.schoolapplication.domain.RussianPassport;
import com.fabit.schoolapplication.domain.Snils;
import com.fabit.schoolapplication.domain.teacher.Teacher;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.application.mapper.TeacherServiceMapper;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TeacherEntityTest {

  @Test
  @DisplayName("Создание TeacherEntity из домена должно работать корректно")
  void schoolClassFromDomainModelTest() {

    Teacher teacherDomain = Teacher.of(
        TeacherId.of(1L),
        FullName.of("Name", "Surname", "Patronymic"),
        RussianPassport.of(
            "1234", "456789",
            LocalDate.of(1980, 9, 15)
        ),
        Snils.of("987-654-321-11"),
        10,
        true);

    TeacherServiceMapper teacherMapper = new TeacherServiceMapper();
    TeacherEntity teacherEntity = teacherMapper.mapDomainToEntity(teacherDomain);

    Assertions.assertEquals(teacherDomain.getStandingYears(), teacherEntity.getStandingYears());
    Assertions.assertEquals(teacherDomain.getSnils().getNumberView(), teacherEntity.getSnils());
    Assertions.assertEquals(teacherDomain.getPassport().toString(), teacherEntity.getPassport());
  }

}
