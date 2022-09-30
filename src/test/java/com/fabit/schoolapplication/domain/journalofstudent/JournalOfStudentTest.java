package com.fabit.schoolapplication.domain.journalofstudent;

import com.fabit.schoolapplication.domain.generalvalueobject.discipline.Discipline;
import com.fabit.schoolapplication.domain.journalofstudent.agregate.JournalOfStudent;
import com.fabit.schoolapplication.domain.journalofstudent.id.JournalByDisciplineId;
import com.fabit.schoolapplication.domain.journalofstudent.id.JournalOfStudentId;
import com.fabit.schoolapplication.domain.student.StudentId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class JournalOfStudentTest {
  JournalOfStudent achievementOfStudent;

  @BeforeEach
  void init() {
    JournalOfStudent achievementOfStudent =
      JournalOfStudent.of(JournalOfStudentId.of(1L), StudentId.of(1L));
    achievementOfStudent.addJournalByDiscipline(
      JournalByDisciplineId.of(1L), Discipline.ALGEBRA);
    this.achievementOfStudent = achievementOfStudent;
  }

  @AfterEach
  void clear() {
    this.achievementOfStudent = null;
  }

  @Test
  @DisplayName(
    "Общей академической успеваемости ученика можно добавить академическую успеваемость по конкретной дисциплине")
  void addAcademicAchievementOfStudentByDiscipline() {
    Assertions.assertEquals(
      Discipline.ALGEBRA,
      achievementOfStudent.getJournalOfStudentByDiscipline(Discipline.ALGEBRA).getDiscipline());
  }

  @Test
  @DisplayName("Ученику можно поставить оценку по дисциплине на дату проведенного урока")
  void addAchievementForStudentByDiscipline() {
    achievementOfStudent.addAchievementForStudentByDiscipline(
      Discipline.ALGEBRA, "5/5", LocalDate.parse("2022-09-12"));
    Assertions.assertEquals(
      "5/5",
      achievementOfStudent
        .getAchievementByDisciplineAndLocalDate(Discipline.ALGEBRA, LocalDate.parse("2022-09-12")).getValue());
  }

  @Test
  @DisplayName("Ученику можно изменить оценку по дисциплине на дату проведенного урока")
  void changeAchievementForStudentByDisciplineAndDateOfLesson() {
    achievementOfStudent.addAchievementForStudentByDiscipline(
      Discipline.ALGEBRA, "5/5", LocalDate.parse("2022-09-12"));

    achievementOfStudent.changeAchievementByDisciplineAndDateOfLesson(
      Discipline.ALGEBRA, "3/3", LocalDate.parse("2022-09-12"));

    Assertions.assertEquals(
      "3/3",
      achievementOfStudent.getAchievementByDisciplineAndLocalDate(Discipline.ALGEBRA, LocalDate.parse("2022-09-12"))
        .getValue());

  }
}
