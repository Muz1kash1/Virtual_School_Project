package com.fabit.schoolapplication.infrastructure.usecase.homeworkforclass;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.educatioprogress.LessonId;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClass;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.SchoolClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.SchoolClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import com.fabit.schoolapplication.infrastructure.usecase.homeworkforclass.mapper.HomeworkForClassMapper;
import com.fabit.schoolapplication.infrastructure.usecase.schoolclass.CreateSchoolClass;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class GetHomeworkForClassTest {


  @Autowired
  HomeworkForClassRepository homeworkForClassRepository;
  @Autowired
  CreateHomeworkForClass createHomeworkForClass;

  @Autowired
  HomeworkForClassMapper homeworkForClassMapper;

  @Autowired
  DeleteHomeworkForClass deleteHomeworkForClass;

  @Autowired
  GetHomeworkForClass getHomeworkForClass;

  @Autowired
  SchoolClassRepository schoolClassRepository;

  @Autowired
  CreateSchoolClass createSchoolClass;

  @BeforeEach
  void cleanAll(){
    homeworkForClassRepository.deleteAll();
    schoolClassRepository.deleteAll();
  }

  @AfterEach
  void clean() {
    homeworkForClassRepository.deleteAll();
    schoolClassRepository.deleteAll();
  }

  @Test
  @DisplayName("Получение урока работает корректно")
  void getHomeworkForClassTest() {
    createSchoolClass.execute(11,"А");

    createHomeworkForClass.execute(
        Discipline.COMPUTING, LocalDate.of(2000, 2, 2),
        SchoolClassId.of(schoolClassRepository.findAll().get(0).getId()));

    Assertions.assertEquals(Discipline.COMPUTING,
        getHomeworkForClass.execute(
                homeworkForClassRepository.findAll().get(0).getId())
            .getDiscipline());
    Assertions.assertEquals(
        homeworkForClassRepository.findAll().get(0).getHomeworkTask(),
        getHomeworkForClass.execute(
            homeworkForClassRepository.findAll().get(0).getId()).getTask()
    );
    Assertions.assertEquals(homeworkForClassRepository.findAll().get(0).getDate(),
        getHomeworkForClass.execute(
            homeworkForClassRepository.findAll().get(0).getId()).getDate()
    );
  }
}
