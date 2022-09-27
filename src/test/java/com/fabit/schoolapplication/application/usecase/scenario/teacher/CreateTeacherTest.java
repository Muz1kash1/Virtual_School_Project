package com.fabit.schoolapplication.application.usecase.scenario.teacher;

import com.fabit.schoolapplication.infrastructure.ui.controller.mapper.TeacherControllerMapper;
import com.fabit.schoolapplication.infrastructure.ui.controller.teacher.dto.FullNameDto;
import com.fabit.schoolapplication.infrastructure.ui.controller.teacher.dto.PassportDto;
import com.fabit.schoolapplication.infrastructure.ui.controller.teacher.dto.SnilsDto;
import com.fabit.schoolapplication.infrastructure.ui.controller.teacher.dto.TeacherDto;
import com.fabit.schoolapplication.domain.teacher.Teacher;
import com.fabit.schoolapplication.infrastructure.persisnence.mapper.TeacherPersistenceMapper;
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
  TeacherControllerMapper teacherControllerMapper;
  @Autowired
  TeacherPersistenceMapper teacherPersistenceMapper;

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
      new PassportDto("1234", "567845", LocalDate.parse("1980-09-15")),
      new SnilsDto("123-456-789-00")
    );

    Teacher teacherDomain = teacherControllerMapper.mapDtoToDomain(teacherDto);

    when(teacherRepository.save(any()))
      .thenReturn(teacherPersistenceMapper.mapDomainToEntity(teacherDomain));

    TeacherEntity teacherCreated = teacherPersistenceMapper.mapDomainToEntity(createTeacher.execute(
        teacherControllerMapper.mapDtoToDomain(teacherDto)
      )
    );

    Assertions.assertEquals(
      teacherDto.getPassport().toString(), teacherCreated.getPassport()
    );
    Assertions.assertEquals(teacherDto.getSnils().getNumberView(), teacherCreated.getSnils());
    Assertions.assertTrue(teacherCreated.isActive());
  }
}