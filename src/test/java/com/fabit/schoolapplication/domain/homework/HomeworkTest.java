package com.fabit.schoolapplication.domain.homework;

import com.fabit.schoolapplication.domain.educatioprogress.LessonId;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HomeworkTest {

  static Homework homework;


  @BeforeAll
  static void initialize() {
    homework = Homework.of(HomeworkId.of(1L),
        StudentId.of(1L), HomeworkForClassId.of(1L));
    homework.uploadTaskCompletionResult("Test");
  }
  @DisplayName("Загрузка выполнения работает корректно")
  @Test
  void uploadCompletedResultTest() {
    Assertions.assertEquals("Test", homework.getTaskCompletionResult());
  }


}
