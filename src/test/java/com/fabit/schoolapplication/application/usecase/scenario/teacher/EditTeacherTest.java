package com.fabit.schoolapplication.application.usecase.scenario.teacher;

import com.fabit.schoolapplication.domain.teacher.Teacher;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.mapper.TeacherPersistenceMapper;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import com.fabit.schoolapplication.infrastructure.ui.controller.mapper.TeacherControllerMapper;
import com.fabit.schoolapplication.infrastructure.ui.controller.teacher.dto.FullNameDto;
import com.fabit.schoolapplication.infrastructure.ui.controller.teacher.dto.PassportDto;
import com.fabit.schoolapplication.infrastructure.ui.controller.teacher.dto.SnilsDto;
import com.fabit.schoolapplication.infrastructure.ui.controller.teacher.dto.TeacherDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;

@RequiredArgsConstructor
@SpringBootTest
public class EditTeacherTest {

  @Autowired
  TeacherControllerMapper teacherControllerMapper;

  @Autowired
  TeacherPersistenceMapper teacherPersistenceMapper;

  @Autowired
  EditTeacher editTeacher;

  @Autowired
  TeacherRepository teacherRepository;

  @Autowired
  CreateTeacher createTeacher;

  TeacherEntity temporaryTeacher;

  long temporaryTeacherId;

  @BeforeEach
  public void init() {

    TeacherDto teacherToCreate = new TeacherDto(
      new FullNameDto("Name", "Surname", "Patronymic"),
      new PassportDto("1234", "567845", LocalDate.parse("1980-09-15")),
      new SnilsDto("123-456-789-00")
    );

    temporaryTeacher = teacherPersistenceMapper.mapDomainToEntity(createTeacher.execute(
        teacherControllerMapper.mapDtoToDomain(teacherToCreate)
      )
    );
    temporaryTeacherId = temporaryTeacher.getId();
  }

  @AfterEach
  public void after() {
    teacherRepository.deleteAll();
  }


  @Test
  @DisplayName("Деактивация и активация учителя должна деактивировать и активировать учителя")
  void deactivateAndActivateTest() {

    Teacher deactivatedTeacher = editTeacher.deactivate(
      temporaryTeacherId, "2022-09-16", "2022-09-25");

    Assertions.assertFalse(deactivatedTeacher.isActive());

    Teacher activatedTeacher = editTeacher.activate(temporaryTeacherId);

    Assertions.assertTrue(activatedTeacher.isActive());
  }

}
