package com.fabit.schoolapplication.domain.educatioprogress;

import com.fabit.schoolapplication.domain.homeworkforclass.LessonId;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EducationProgressTest {

  @Test
  @DisplayName("Создание успеваемости должно создавать корретный объект")
  public void educationProgressOfTest() {
    EducationProgress educationProgress = EducationProgress.of(
        EducationProgressId.of(1L),
        StudentId.of(357L),
        TeacherId.of(81L),
        LessonId.of(277L),
        List.of(
            Mark.of(MarkId.of(1092L), EducationProgressId.of(1L), 5),
            Mark.of(MarkId.of(1092L), EducationProgressId.of(1L), 4))
    );

    Assertions.assertEquals(1L, educationProgress.getEducationProgressId().getValue());
    Assertions.assertEquals(2, educationProgress.getMarks().size());
    Assertions.assertEquals(357L, educationProgress.getStudentId().getValue());
    Assertions.assertEquals(81L, educationProgress.getTeacherId().getValue());
    Assertions.assertEquals(277L, educationProgress.getLessonId().getValue());
    Assertions.assertEquals(5, educationProgress.getMarks().get(0).getValue());
  }

}
