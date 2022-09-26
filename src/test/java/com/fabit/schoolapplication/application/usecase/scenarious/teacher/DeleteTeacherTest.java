package com.fabit.schoolapplication.application.usecase.scenarious.teacher;

import com.fabit.schoolapplication.infrastructure.controller.virtualschool.teacher.dto.FullNameDto;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.teacher.dto.PassportDto;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.teacher.dto.SnilsDto;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.teacher.dto.TeacherDto;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import java.time.LocalDate;

@RequiredArgsConstructor
@SpringBootTest
public class DeleteTeacherTest {

  @Autowired
  DeleteTeacher deleteTeacher;

  @Autowired
  CreateTeacher createTeacher;

  @Autowired
  TeacherRepository teacherRepository;

  @AfterEach
  public void after() {
    teacherRepository.deleteAll();
  }

  @Test
  @Transactional
  @DisplayName("Удаление учителя должно удалять учителя")
  void deleteTeacherTest() {

    TeacherDto teacherDto = new TeacherDto(
      new FullNameDto("Name", "Surname", "Patronymic"),
      new PassportDto("1234", "567845", LocalDate.of(1980, 9, 15)),
      new SnilsDto("123-456-789-00")
    );

    createTeacher.execute(teacherDto);

    long createdTeacherId = teacherRepository.findAll().get(0).getId();

    Assertions.assertEquals(1, teacherRepository.findAll().size());

    deleteTeacher.execute(createdTeacherId);

    Assertions.assertEquals(0, teacherRepository.findAll().size());
  }

}
