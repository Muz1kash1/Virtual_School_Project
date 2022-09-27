package com.fabit.schoolapplication.domain.academicachevementofstudent;

import com.fabit.schoolapplication.domain.generalvalueobject.discipline.Discipline;
import com.fabit.schoolapplication.domain.academicachevementofstudent.agregate.AcademicAchievementOfStudent;
import com.fabit.schoolapplication.domain.academicachevementofstudent.id.AcademicAchievementByDisciplineId;
import com.fabit.schoolapplication.domain.academicachevementofstudent.id.AcademicAchievementOfStudentId;
import com.fabit.schoolapplication.domain.student.StudentId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class AcademicAchievementOfStudentTest {
  AcademicAchievementOfStudent achievementOfStudent;

  @BeforeEach
  void init() {
    AcademicAchievementOfStudent achievementOfStudent =
      AcademicAchievementOfStudent.of(AcademicAchievementOfStudentId.of(1L), StudentId.of(1L));
    achievementOfStudent.addAcademicAchievementByDiscipline(
      AcademicAchievementByDisciplineId.of(1L), Discipline.ALGEBRA);
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
      achievementOfStudent.getAcademicAchievementOfStudentByDiscipline(Discipline.ALGEBRA).getDiscipline());
  }

  @Test
  @DisplayName("Ученику можно поставить оценку по дисциплине на дату проведенного урока")
  void addAchievementForStudentByDiscipline() {
    achievementOfStudent.addAchievementForStudentByDiscipline(
      Discipline.ALGEBRA, "5/5", LocalDate.of(2022, 9, 12));
    Assertions.assertEquals(
      "5/5",
      achievementOfStudent
        .getAchievementByDisciplineAndLocalDate(Discipline.ALGEBRA, LocalDate.of(2022, 9, 12)).getValue());
  }

  @Test
  @DisplayName("Ученику можно изменить оценку по дисциплине на дату проведенного урока")
  void changeAchievementForStudentByDisciplineAndDateOfLesson() {
    achievementOfStudent.addAchievementForStudentByDiscipline(
      Discipline.ALGEBRA, "5/5", LocalDate.of(2022, 9, 12));

    achievementOfStudent.changeAchievementByDisciplineAndDateOfLesson(
      Discipline.ALGEBRA, "3/3", LocalDate.of(2022, 9, 12));

    Assertions.assertEquals(
      "3/3",
      achievementOfStudent.getAchievementByDisciplineAndLocalDate(Discipline.ALGEBRA, LocalDate.of(2022, 9, 12))
        .getValue());

  }
}
