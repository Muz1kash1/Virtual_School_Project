package com.fabit.schoolapplication.domain.schedule.entity;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.schedule.valueobject.LessonInSchedule;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class LessonsForSchoolClassTest {


  @Test @DisplayName("Создание уроков для конкретного класса")
  public void successCreateSchoolDayForSchoolClass() {
    List<LessonInSchedule> lessons = new ArrayList<>();
    lessons.add(LessonInSchedule.of(5, Discipline.ALGEBRA, TeacherId.of(1)));
    lessons.add(LessonInSchedule.of(3, Discipline.CHEMISTRY, TeacherId.of(2)));

    LessonsForSchoolClass lessonsForSchoolClass = new LessonsForSchoolClass(
      LessonsForSchoolClassId.of(1L),
      SchoolClassName.of(3, "А"),
      lessons
    );

    assertThat(lessonsForSchoolClass.getLessonsInScheduleList().size())
      .isNotNull()
      .as("check length")
      .isEqualTo(lessonsForSchoolClass.getLessonsInScheduleList().size());
    assertThat(lessonsForSchoolClass).isNotNull();
  }

  @Test @DisplayName("Создание пустого списка с уроками для класса")
  public void failureCreateSchoolDayForSchoolClass_lessonSizeZero() {
    List<LessonInSchedule> lessons = new ArrayList<>();
    Throwable throwable = catchThrowable(
      () -> new LessonsForSchoolClass(LessonsForSchoolClassId.of(1L), SchoolClassName.of(5, "A"), lessons));

    assertThat(throwable).isInstanceOf(RuntimeException.class);
    assertThat(throwable.getMessage()).isEqualTo("Неверный формат данных для создания названия класса");
  }
}
