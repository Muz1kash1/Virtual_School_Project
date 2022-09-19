package com.fabit.schoolapplication.domain.homeworkforclass.event;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClass;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HomeworkForClassEventTest {

  @BeforeEach
  void clearLessonEvents(){
    HomeworkForClass.domainEvents.clear();
  }

  @Test
  @DisplayName("Создание урока должно выкидывать верный ивент с правильным content")
  void LessonCreatedEventTest() {
    HomeworkForClass homeworkForClass = HomeworkForClass.of(Discipline.ALGEBRA, LocalDate.of(2000,2,2));
    HomeworkForClass homeworkForClassContent = (HomeworkForClass) HomeworkForClass.domainEvents.get(0).getContent();

    Assertions.assertEquals(1, HomeworkForClass.domainEvents.size());
    Assertions.assertEquals(homeworkForClassContent, homeworkForClass);
    Assertions.assertEquals(
        homeworkForClass.getDiscipline(), homeworkForClassContent.getDiscipline()
    );
    Assertions.assertEquals( homeworkForClass.getDate(),homeworkForClassContent.getDate());


    HomeworkForClass homeworkForClass1 =
        HomeworkForClass.of(Discipline.BIOLOGY, LocalDate.of(2000,2,2));
    HomeworkForClass homeworkForClassContent1 = (HomeworkForClass) HomeworkForClass.domainEvents.get(1).getContent();

    Assertions.assertEquals(2, HomeworkForClass.domainEvents.size());
    Assertions.assertEquals(homeworkForClassContent1, homeworkForClass1);
    Assertions.assertEquals(
        homeworkForClass1.getDiscipline(), homeworkForClassContent1.getDiscipline()
    );
    Assertions.assertEquals(homeworkForClass1.getDate(),homeworkForClassContent1.getDate());
  }

  @Test
  @DisplayName("Изменение домашнего задания на урок доложно выкидывать соответствующий ивент с верным content")
  void HomeworkTaskSetEventTest(){
    HomeworkForClass homeworkForClass = HomeworkForClass.of(Discipline.COMPUTING, LocalDate.of(2000,2,2));
    HomeworkForClass homeworkForClassContent = (HomeworkForClass) HomeworkForClass.domainEvents.get(0).getContent();
    homeworkForClass.setHomeworkText("Test");

    Assertions.assertEquals(2, HomeworkForClass.domainEvents.size());
    Assertions.assertEquals("Test", homeworkForClassContent.getTask());
  }
}
