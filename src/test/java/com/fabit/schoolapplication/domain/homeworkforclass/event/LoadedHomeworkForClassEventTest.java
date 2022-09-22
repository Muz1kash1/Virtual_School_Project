package com.fabit.schoolapplication.domain.homeworkforclass.event;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClass;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoadedHomeworkForClassEventTest {

  @BeforeEach
  void clearHomeworkForClassEvents() {
    HomeworkForClass.domainEvents.clear();
  }

  @Test
  @DisplayName("Создание домашнего задания для класса должно выкидывать верный ивент с правильным content")
  void HomeworkForClassCreatedEventTest() {

    HomeworkForClass homeworkForClass = HomeworkForClass.of(
        Discipline.ALGEBRA,
        LocalDate.of(2000, 2, 2),
        SchoolClassId.of(1L), HomeworkForClassId.of(1L)
    );

    HomeworkForClass homeworkForClassContent
        = (HomeworkForClass) HomeworkForClass.domainEvents.get(0).getContent();

    Assertions.assertEquals(1, HomeworkForClass.domainEvents.size());
    Assertions.assertEquals(homeworkForClassContent, homeworkForClass);
    Assertions.assertEquals(
        homeworkForClass.getDiscipline(),
        homeworkForClassContent.getDiscipline()
    );
    Assertions.assertEquals(homeworkForClass.getDate(), homeworkForClassContent.getDate());
    Assertions.assertEquals(
        homeworkForClass.getId().getValue(),
        homeworkForClassContent.getId().getValue()
    );
    Assertions.assertEquals(
        homeworkForClass.getSchoolClassId().getValue(),
        homeworkForClassContent.getSchoolClassId().getValue()
    );

    HomeworkForClass homeworkForClass1
        = HomeworkForClass.of(
        Discipline.BIOLOGY,
        LocalDate.of(2000, 2, 2),
        SchoolClassId.of(2L),
        HomeworkForClassId.of(2L)
    );

    HomeworkForClass homeworkForClassContent1
        = (HomeworkForClass) HomeworkForClass.domainEvents.get(1).getContent();

    Assertions.assertEquals(2, HomeworkForClass.domainEvents.size());
    Assertions.assertEquals(homeworkForClassContent1, homeworkForClass1);
    Assertions.assertEquals(
        homeworkForClass1.getDiscipline(), homeworkForClassContent1.getDiscipline()
    );
    Assertions.assertEquals(homeworkForClass1.getDate(), homeworkForClassContent1.getDate());
    Assertions.assertEquals(
        homeworkForClass1.getSchoolClassId().getValue(),
        homeworkForClassContent1.getSchoolClassId().getValue());
    Assertions.assertEquals(
        homeworkForClass1.getId().getValue(),
        homeworkForClassContent1.getId().getValue());
  }

  @Test
  @DisplayName("Изменение домашнего задания должно выкидывать соответствующий ивент с верным content")
  void HomeworkTaskSetEventTest() {
    HomeworkForClass homeworkForClass = HomeworkForClass.of(
        Discipline.COMPUTING,
        LocalDate.of(2000, 2, 2),
        SchoolClassId.of(1L),
        HomeworkForClassId.of(1L)
    );

    HomeworkForClass homeworkForClassContent
        = (HomeworkForClass) HomeworkForClass.domainEvents.get(0).getContent();

    homeworkForClass.setHomeworkText("Test");

    Assertions.assertEquals(2, HomeworkForClass.domainEvents.size());
    Assertions.assertEquals("Test", homeworkForClassContent.getTask());
  }
}
