package com.fabit.schoolapplication.application.usecase.scenarious.teacher;

import com.fabit.schoolapplication.application.mapper.TeacherServiceMapper;
import com.fabit.schoolapplication.domain.teacher.Teacher;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.teacher.dto.FullNameDto;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.teacher.dto.PassportDto;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.teacher.dto.SnilsDto;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.teacher.dto.TeacherDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.time.LocalDate;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RequiredArgsConstructor
@SpringBootTest
public class CreateTeacherTest {
  @Autowired
  TeacherServiceMapper teacherMapper;

  @Autowired
  CreateTeacher createTeacher;

  @MockBean
  TeacherRepository teacherRepository;

  @AfterEach
  public void after() {
    teacherRepository.deleteAll();
  }

  @Test
  @DisplayName("Создание учителя юзкейсом должно создавать корректного учителя")
  void createTeacherTest() {


    TeacherDto teacherDto = new TeacherDto(
      new FullNameDto("Name", "Surname", "Patronymic"),
      new PassportDto("1234", "567845", LocalDate.of(1980, 9, 15)),
      new SnilsDto("123-456-789-00")
    );

    Teacher teacherDomain = teacherMapper.mapDtoToDomain(teacherDto);

    when(teacherRepository.save(any()))
      .thenReturn(teacherMapper.mapDomainToEntity(teacherDomain));

    TeacherEntity teacherCreated = createTeacher.execute(teacherDto);

    Assertions.assertEquals(
      teacherDto.getPassport().toString(), teacherCreated.getPassport()
    );
    Assertions.assertEquals(teacherDto.getSnils().getNumberView(), teacherCreated.getSnils());
    Assertions.assertTrue(teacherCreated.isActive());
  }
}