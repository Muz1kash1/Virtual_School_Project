package com.fabit.schoolapplication.domain.schedule.valueobject;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class LessonInScheduleTest {


  @Test
  @DisplayName("Создание урока по расписанию")
  public void success_create_lessonOnSchedule() {
    LessonInSchedule lesson =
      LessonInSchedule.of(
        5,
        Discipline.ALGEBRA,
        TeacherId.of(1)
      );

    assertThat(lesson).as("check null").isNotNull();
    assertThat(lesson.getLessonNumber()).as("check lesson number").isEqualTo(5);
    assertThat(lesson.getDiscipline()).as("check discipline").isEqualTo(Discipline.ALGEBRA);
    assertThat(lesson.getTeacherId()).as("check teacher id").isEqualTo(TeacherId.of(1));
  }

  @Test
  @DisplayName("Создание урока с недопустимым порядковым номером")
  public void failureCreateLessonOnSchedule_numberLesson10_failure() {
    Throwable throwable = catchThrowable(() ->
      LessonInSchedule.of(
        10,
        Discipline.ALGEBRA,
        TeacherId.of(1)
      )
    );
    assertThat(throwable).isInstanceOf(RuntimeException.class);
    assertThat(throwable.getMessage()).isEqualTo("Номер урока должен быть в диапазоне от 1 до 10");
  }

}
