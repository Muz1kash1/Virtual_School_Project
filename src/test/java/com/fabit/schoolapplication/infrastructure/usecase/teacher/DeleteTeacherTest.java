package com.fabit.schoolapplication.infrastructure.usecase.teacher;

import com.fabit.schoolapplication.infrastructure.controller.teacher.dto.FullNameDto;
import com.fabit.schoolapplication.infrastructure.controller.teacher.dto.PassportDto;
import com.fabit.schoolapplication.infrastructure.controller.teacher.dto.SnilsDto;
import com.fabit.schoolapplication.infrastructure.controller.teacher.dto.TeacherDto;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        10,
        new FullNameDto("Name", "Surname", "Patronymic"),
        new PassportDto("1234", "5678"),
        new SnilsDto("1234567890"),
        true
    );
    createTeacher.execute(teacherDto);
    long createdTeacherId = teacherRepository.findAll().get(0).getId();
    Assertions.assertEquals(1, teacherRepository.findAll().size());

    deleteTeacher.execute(createdTeacherId);
    Assertions.assertEquals(0, teacherRepository.findAll().size());
  }

}