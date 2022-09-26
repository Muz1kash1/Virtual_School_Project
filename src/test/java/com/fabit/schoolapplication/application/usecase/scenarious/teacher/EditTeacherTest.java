package com.fabit.schoolapplication.application.usecase.scenarious.teacher;

import com.fabit.schoolapplication.infrastructure.controller.virtualschool.teacher.dto.DeactivateDto;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.teacher.dto.FullNameDto;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.teacher.dto.PassportDto;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.teacher.dto.SnilsDto;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.teacher.dto.TeacherDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
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
      new PassportDto("1234", "567845", LocalDate.of(1980, 9, 15)),
      new SnilsDto("123-456-789-00")
    );

    temporaryTeacher = createTeacher.execute(teacherToCreate);
    temporaryTeacherId = temporaryTeacher.getId();
  }

  @AfterEach
  public void after() {
    teacherRepository.deleteAll();
  }


  @Test
  @DisplayName("Деактивация и активация учителя должна деактивировать и активировать учителя")
  void deactivateAndActivateTest() {

    TeacherEntity deactivatedTeacher = editTeacher.deactivate(
      new DeactivateDto(temporaryTeacherId, "2022-09-16", "2022-09-25"));

    Assertions.assertFalse(deactivatedTeacher.isActive());

    TeacherEntity activatedTeacher = editTeacher.activate(temporaryTeacherId);

    Assertions.assertTrue(activatedTeacher.isActive());
  }

}
