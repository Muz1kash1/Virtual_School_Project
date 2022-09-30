package com.fabit.schoolapplication.domain.journalofstudent.agregate;

import com.fabit.schoolapplication.domain.generalvalueobject.discipline.Discipline;
import com.fabit.schoolapplication.domain.journalofstudent.entity.JournalByDiscipline;
import com.fabit.schoolapplication.domain.journalofstudent.id.JournalByDisciplineId;
import com.fabit.schoolapplication.domain.journalofstudent.id.JournalOfStudentId;
import com.fabit.schoolapplication.domain.journalofstudent.valueobject.Achievement;
import com.fabit.schoolapplication.domain.student.StudentId;
import lombok.Getter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Агрегат общей успеваемости ученика по всем дисциплинам.
 *
 * @author SmirnovMA
 */
@Getter
public final class JournalOfStudent {

  private final JournalOfStudentId id;
  private final StudentId studentId;
  private List<JournalByDiscipline> journalByDisciplineList;

  private JournalOfStudent(
    JournalOfStudentId id, StudentId studentId
  ) {
    this.id = id;
    this.studentId = studentId;
    this.journalByDisciplineList = new ArrayList<>();
  }

  /**
   * Статическая фабрика.
   *
   * @param id        id общей академической успеваемости студента
   * @param studentId id студента
   * @return объект общей академической успеваемости
   */
  public static JournalOfStudent of(
    JournalOfStudentId id,
    StudentId studentId
  ) {
    return new JournalOfStudent(
      id, studentId);
  }

  /**
   * метод для получения копии объекта по полученным данным.
   *
   * @param id                      id общей академической успеваемости студента
   * @param studentId               id студента
   * @param journalByDisciplineList список журналов академической успеваемости студента по конкретной дисциплине
   * @return объект общей академической успеваемости
   */
  public static JournalOfStudent copyOf(
    JournalOfStudentId id,
    StudentId studentId,
    List<JournalByDiscipline> journalByDisciplineList
  ) {

    JournalOfStudent achievementOfStudent
      = new JournalOfStudent(id, studentId);

    achievementOfStudent.journalByDisciplineList = journalByDisciplineList;

    return achievementOfStudent;
  }

  /**
   * Добавить в список журналов академической успеваемости студента по конкретной дисциплине новую дисциплину.
   *
   * @param academicAchievementByDisciplineId id академической успеваемости студента по конкретной дисциплине
   * @param discipline                        дисциплина
   */
  public void addJournalByDiscipline(
    JournalByDisciplineId academicAchievementByDisciplineId,
    Discipline discipline
  ) {

    if (journalByDisciplineList
      .stream()
      .noneMatch(item -> item.getDiscipline() == discipline)
    ) {
      journalByDisciplineList.add(JournalByDiscipline.of(
        academicAchievementByDisciplineId, discipline
      ));
    } else {
      throw new IllegalArgumentException(
        "Такой объект успеваемости ученика по конкретной дисциплине уже есть в списке");
    }
  }

  /**
   * Поставить отметку ученику по дисциплине на дату урока.
   *
   * @param discipline   дисциплина
   * @param achievement  строковое представление оценки
   * @param dateOfLesson дата урока
   */
  public void addAchievementForStudentByDiscipline(
    Discipline discipline, String achievement, LocalDate dateOfLesson
  ) {

    JournalByDiscipline journalByDiscipline
      = getJournalOfStudentByDiscipline(discipline);

    journalByDiscipline.addAchievement(achievement, dateOfLesson);
  }

  /**
   * Изменить отметку ученику по дисциплине на дату урока.
   *
   * @param discipline   дисциплина
   * @param achievement  строковое представление оценки
   * @param dateOfLesson дата урока
   */
  public void changeAchievementByDisciplineAndDateOfLesson(
    Discipline discipline, String achievement, LocalDate dateOfLesson
  ) {

    JournalByDiscipline journalByDiscipline
      = getJournalOfStudentByDiscipline(discipline);

    journalByDiscipline.changeAchievement(achievement, dateOfLesson);
  }

  /**
   * Получить список отметок ученика по дисциплине.
   *
   * @param discipline дисциплина
   * @return список отметок
   */
  public List<Achievement> getAllAchievementsByDiscipline(
    Discipline discipline
  ) {

    JournalByDiscipline journalByDiscipline
      = getJournalOfStudentByDiscipline(discipline);

    return journalByDiscipline.getAchievements();
  }

  /**
   * Получить отметку ученика по дисциплине на дату урока.
   *
   * @param dateOfLesson дата урока
   * @param discipline   дисциплина
   * @return отметка
   */
  public Achievement getAchievementByDisciplineAndLocalDate(
    Discipline discipline, LocalDate dateOfLesson
  ) {

    JournalByDiscipline journalByDiscipline
      = getJournalOfStudentByDiscipline(discipline);

    return journalByDiscipline.getAchievementByDateOfLesson(dateOfLesson);
  }

  /**
   * Получить объект журнала академической успеваемости студента по конкретной дисциплине.
   *
   * @param discipline дисциплина
   * @return объект журнала академической успеваемости студента по конкретной дисциплине
   */
  public JournalByDiscipline getJournalOfStudentByDiscipline(
    Discipline discipline
  ) {

    Optional<JournalByDiscipline> academicAchievementByDiscipline
      = journalByDisciplineList
      .stream()
      .filter(item -> item.getDiscipline().equals(discipline))
      .findFirst();

    if (academicAchievementByDiscipline.isPresent()) {
      return academicAchievementByDiscipline.get();
    } else {
      throw new IllegalArgumentException(
        "Журнала успеваемости ученика по данной дисциплине у ученика нет!"
      );
    }
  }
}
