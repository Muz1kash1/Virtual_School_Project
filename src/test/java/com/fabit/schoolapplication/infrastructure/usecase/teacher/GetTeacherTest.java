package com.fabit.schoolapplication.infrastructure.usecase.teacher;

import com.fabit.schoolapplication.infrastructure.controller.teacher.dto.FullNameDto;
import com.fabit.schoolapplication.infrastructure.controller.teacher.dto.PassportDto;
import com.fabit.schoolapplication.infrastructure.controller.teacher.dto.SnilsDto;
import com.fabit.schoolapplication.infrastructure.controller.teacher.dto.TeacherDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RequiredArgsConstructor
@SpringBootTest
public class GetTeacherTest {

  @Autowired
  GetTeacher getTeacher;
  @Autowired
  CreateTeacher createTeacher;
  @Autowired
  TeacherRepository teacherRepository;

  TeacherEntity temporaryTeacher1;
  TeacherEntity temporaryTeacher2;

  @BeforeEach
  public void init() {
    TeacherDto teacherToCreate1 = new TeacherDto(
        10,
        new FullNameDto("Name", "Surname", "Patronymic"),
        new PassportDto("1234", "5678"),
        new SnilsDto("1234567890"),
        true
    );
    TeacherDto teacherToCreate2 = new TeacherDto(
        18,
        new FullNameDto("SName", "SSurname", "SPatronymic"),
        new PassportDto("4231", "1523"),
        new SnilsDto("24607823"),
        false
    );
    temporaryTeacher1 = createTeacher.execute(teacherToCreate1);
    temporaryTeacher2 = createTeacher.execute(teacherToCreate2);
  }

  @AfterEach
  public void after() {
    teacherRepository.deleteAll();
  }

  @Test
  @DisplayName("Получение списка всех учителей должно возвращать актуальный список")
  void getAllTeachersTest() {
    List<TeacherEntity> teachers = getTeacher.all();
    Assertions.assertEquals(2, teachers.size());
    Assertions.assertTrue(temporaryTeacher1.isActive());
    Assertions.assertFalse(temporaryTeacher2.isActive());
    Assertions.assertEquals("1234567890", temporaryTeacher1.getSnils());
  }

  @Test
  @DisplayName("Получение учителя по id должно возвращать соответствующего учителя")
  void getTeacherByIdTest() {
    TeacherEntity resultedTeacher = getTeacher.byId(temporaryTeacher1.getId());
    Assertions.assertEquals(temporaryTeacher1, resultedTeacher);
  }

}
