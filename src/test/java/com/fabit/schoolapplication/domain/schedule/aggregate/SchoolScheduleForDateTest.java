package com.fabit.schoolapplication.domain.schedule.aggregate;

import static org.assertj.core.api.Assertions.assertThat;

import com.fabit.schoolapplication.domain.generalvalueobject.discipline.Discipline;
import com.fabit.schoolapplication.domain.schedule.entity.LessonsForSchoolClass;
import com.fabit.schoolapplication.domain.schedule.entity.LessonsForSchoolClassId;
import com.fabit.schoolapplication.domain.schedule.valueobject.LessonInSchedule;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SchoolScheduleForDateTest {


  @Test
  @DisplayName("Создание расписания на день для нескольких классов")
  public void createSchedule_success() {
    List<LessonInSchedule> lessons = new ArrayList<>();
    lessons.add(LessonInSchedule.of(5, Discipline.ALGEBRA, TeacherId.of(1)));
    lessons.add(LessonInSchedule.of(3, Discipline.CHEMISTRY, TeacherId.of(2)));

    LessonsForSchoolClass lessonsForSchoolClass = new LessonsForSchoolClass(
      LessonsForSchoolClassId.of(1L),
      SchoolClassName.of(3, "А"),
      lessons
    );

    SchoolScheduleForDate schedule = new SchoolScheduleForDate(
      SchoolScheduleForDateId.of(12L),
      LocalDate.of(2022, 3, 12),
      List.of(lessonsForSchoolClass)
    );
    assertThat(schedule.getLessonsForSchoolClasses())
      .isNotNull()
      .contains(lessonsForSchoolClass)
      .hasSize(1);
  }

  @Test
  @DisplayName("Успешное выставление урока в расписание")
  public void successScheduleLesson() {
    List<LessonInSchedule> lessons = new ArrayList<>();
    lessons.add(LessonInSchedule.of(1, Discipline.ALGEBRA, TeacherId.of(1)));
    lessons.add(LessonInSchedule.of(2, Discipline.CHEMISTRY, TeacherId.of(2)));

    LessonsForSchoolClass lessonsForSchoolClass = new LessonsForSchoolClass(
      LessonsForSchoolClassId.of(1L),
      SchoolClassName.of(3, "А"),
      lessons
    );

    SchoolScheduleForDate schedule = new SchoolScheduleForDate(
      SchoolScheduleForDateId.of(12L),
      LocalDate.of(2022, 3, 12),
      List.of(lessonsForSchoolClass)
    );
    schedule.scheduleLesson(
      SchoolClassName.of(3, "А"),
      3,
      Discipline.CHEMISTRY,
      TeacherId.of(12)
    );

    assertThat(schedule.getLessonsForSchoolClasses().get(0)
      .getLessonsInScheduleList().size())
      .as("check length")
      .isEqualTo(3);
    assertThat(schedule.getLessonsForSchoolClasses().get(0)
      .getLessonsInScheduleList().get(2).getDiscipline())
      .as("check discipline")
      .isEqualTo(Discipline.CHEMISTRY);
  }

  @Test
  @DisplayName("Отмена урока урока в расписание")
  public void cancelScheduleLesson() {
    List<LessonInSchedule> lessons = new ArrayList<>();
    lessons.add(LessonInSchedule.of(1, Discipline.ALGEBRA, TeacherId.of(1)));
    lessons.add(LessonInSchedule.of(2, Discipline.CHEMISTRY, TeacherId.of(2)));

    LessonsForSchoolClass lessonsForSchoolClass = new LessonsForSchoolClass(
      LessonsForSchoolClassId.of(1L),
      SchoolClassName.of(3, "А"),
      lessons
    );

    SchoolScheduleForDate schedule = new SchoolScheduleForDate(
      SchoolScheduleForDateId.of(12L),
      LocalDate.of(2022, 3, 12),
      List.of(lessonsForSchoolClass)
    );
    schedule.cancelLesson(
      SchoolClassName.of(3, "А"),
      LessonInSchedule.of(2, Discipline.CHEMISTRY, TeacherId.of(2))
    );

    assertThat(schedule.getLessonsForSchoolClasses().get(0)
      .getLessonsInScheduleList().size())
      .as("check length")
      .isEqualTo(1);
  }
}
