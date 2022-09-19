package com.fabit.schoolapplication.infrastructure.usecase.teacher;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.fabit.schoolapplication.domain.teacher.Teacher;
import com.fabit.schoolapplication.infrastructure.controller.teacher.dto.FullNameDto;
import com.fabit.schoolapplication.infrastructure.controller.teacher.dto.PassportDto;
import com.fabit.schoolapplication.infrastructure.controller.teacher.dto.SnilsDto;
import com.fabit.schoolapplication.infrastructure.controller.teacher.dto.TeacherDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import com.fabit.schoolapplication.infrastructure.usecase.teacher.mapper.TeacherServiceMapper;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@RequiredArgsConstructor
@SpringBootTest
public class CreateTeacherTest {

  @Autowired
  CreateTeacher createTeacher;
  @MockBean
  TeacherRepository teacherRepository;

  @Test
  @DisplayName("Создание учителя юзкейсом должно создавать корректного учителя")
  void createTeacherTest() {
    TeacherDto teacherDto = new TeacherDto(
        10,
        new FullNameDto("Name", "Surname", "Patronymic"),
        new PassportDto("1234", "5678", LocalDate.of(1980, 9, 15)),
        new SnilsDto("1234567890"),
        true
    );
    TeacherServiceMapper teacherMapper = new TeacherServiceMapper();

    Teacher teacherDomain = teacherMapper.mapDtoToDomain(teacherDto);
    when(teacherRepository.save(any()))
        .thenReturn(teacherMapper.mapDomainToEntity(teacherDomain));

    TeacherEntity teacherCreated = createTeacher.execute(teacherDto);

    Assertions.assertEquals(
        teacherDto.getPassport().toString(), teacherCreated.getPassport());
    Assertions.assertEquals(teacherDto.getStandingYears(), teacherCreated.getStandingYears());
    Assertions.assertEquals(teacherDto.getSnils().getNumberView(), teacherCreated.getSnils());
    Assertions.assertTrue(teacherCreated.isActive());
  }

}
