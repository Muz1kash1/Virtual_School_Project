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
import java.util.List;

@RequiredArgsConstructor
@SpringBootTest
public class GetTeacherTest {

  @Autowired
  TeacherControllerMapper teacherControllerMapper;

  @Autowired
  TeacherPersistenceMapper teacherPersistenceMapper;

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
      new FullNameDto("Name", "Surname", "Patronymic"),
      new PassportDto("1234", "567845", LocalDate.parse("1980-09-15")),
      new SnilsDto("123-456-789-00")
    );

    TeacherDto teacherToCreate2 = new TeacherDto(
      new FullNameDto("SName", "SSurname", "SPatronymic"),
      new PassportDto("4231", "152345", LocalDate.parse("1980-10-15")),
      new SnilsDto("246-078-233-00")
    );

    temporaryTeacher1 = teacherPersistenceMapper.mapDomainToEntity(createTeacher.execute(
        teacherControllerMapper.mapDtoToDomain(teacherToCreate1)
      )
    );
    temporaryTeacher2 = teacherPersistenceMapper.mapDomainToEntity(createTeacher.execute(
        teacherControllerMapper.mapDtoToDomain(
          teacherToCreate2)
      )
    );
  }

  @AfterEach
  public void after() {
    teacherRepository.deleteAll();
  }

  @Test
  @DisplayName("Получение списка всех учителей должно возвращать актуальный список")
  void getAllTeachersTest() {

    List<Teacher> teachers = getTeacher.all();

    Assertions.assertEquals(2, teachers.size());
    Assertions.assertTrue(temporaryTeacher1.isActive());
    Assertions.assertTrue(temporaryTeacher2.isActive());
    Assertions.assertEquals("123-456-789-00", temporaryTeacher1.getSnils());
  }

  @Test
  @DisplayName("Получение учителя по id должно возвращать соответствующего учителя")
  void getTeacherByIdTest() {
    Teacher resultedTeacher = getTeacher.byId(temporaryTeacher1.getId());
    Assertions.assertEquals(
      teacherPersistenceMapper.mapEntityToDomain(temporaryTeacher1).getId(),
      resultedTeacher.getId()
    );
  }
}