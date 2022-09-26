package com.fabit.schoolapplication.domain.academicachevementofstudent.agregate;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.academicachevementofstudent.entity.AcademicAchievementByDiscipline;
import com.fabit.schoolapplication.domain.academicachevementofstudent.id.AcademicAchievementByDisciplineId;
import com.fabit.schoolapplication.domain.academicachevementofstudent.id.AcademicAchievementOfStudentId;
import com.fabit.schoolapplication.domain.academicachevementofstudent.valueobject.Achievement;
import com.fabit.schoolapplication.domain.student.StudentId;
import lombok.Getter;
import org.webjars.NotFoundException;
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
public final class AcademicAchievementOfStudent {
  private final AcademicAchievementOfStudentId id;
  private final StudentId studentId;
  private List<AcademicAchievementByDiscipline>
    academicAchievementByDisciplineList;

  private AcademicAchievementOfStudent(
    AcademicAchievementOfStudentId id,
    StudentId studentId) {
    this.id = id;
    this.studentId = studentId;
    this.academicAchievementByDisciplineList = new ArrayList<>();
  }

  /**
   * Статическая фабрика.
   *
   * @param id        id общей академической успеваемости студента
   * @param studentId id студента
   * @return объект общей академической успеваемости
   */
  public static AcademicAchievementOfStudent of(
    AcademicAchievementOfStudentId id,
    StudentId studentId) {
    return new AcademicAchievementOfStudent(
      id, studentId);
  }

  /**
   * метод для получения копии объекта по полученным данным
   *
   * @param id                                  id общей академической успеваемости студента
   * @param studentId                           id студента
   * @param academicAchievementByDisciplineList список журналов академической успеваемости
   *                                            студента по конкретной дисциплине
   * @return объект общей академической успеваемости
   */
  public static AcademicAchievementOfStudent copyOf(
    AcademicAchievementOfStudentId id,
    StudentId studentId,
    List<AcademicAchievementByDiscipline> academicAchievementByDisciplineList) {
    AcademicAchievementOfStudent achievementOfStudent =
      new AcademicAchievementOfStudent(id, studentId);
    achievementOfStudent.academicAchievementByDisciplineList = academicAchievementByDisciplineList;
    return achievementOfStudent;
  }

  /**
   * Добавить в список журналов академической успеваемости студента по конкретной дисциплине новую дисциплину.
   *
   * @param academicAchievementByDisciplineId id академической успеваемости студента по конкретной дисциплине
   * @param discipline                        дисциплина
   */
  public void addAcademicAchievementByDiscipline(
    AcademicAchievementByDisciplineId academicAchievementByDisciplineId,
    Discipline discipline) {
    if (this.academicAchievementByDisciplineList.stream()
      .noneMatch(item -> item.getDiscipline() == discipline)) {
      this.academicAchievementByDisciplineList.add(
        AcademicAchievementByDiscipline.of(
          academicAchievementByDisciplineId, discipline));
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
    Discipline discipline, String achievement, LocalDate dateOfLesson) {
    AcademicAchievementByDiscipline academicAchievementByDiscipline =
      getAcademicAchievementOfStudentByDiscipline(discipline);
    academicAchievementByDiscipline.addAchievement(achievement, dateOfLesson);
  }

  /**
   * Изменить отметку ученику по дисциплине на дату урока.
   *
   * @param discipline   дисциплина
   * @param achievement  строковое представление оценки
   * @param dateOfLesson дата урока
   */
  public void changeAchievementByDisciplineAndDateOfLesson(
    Discipline discipline, String achievement, LocalDate dateOfLesson) {
    AcademicAchievementByDiscipline academicAchievementByDiscipline =
      getAcademicAchievementOfStudentByDiscipline(discipline);
    academicAchievementByDiscipline.changeAchievement(achievement, dateOfLesson);
  }

  /**
   * Получить список отметок ученика по дисциплине.
   *
   * @param discipline дисциплина
   * @return список отметок
   */
  public List<Achievement> getAllAchievementsByDiscipline(Discipline discipline) {
    AcademicAchievementByDiscipline academicAchievementByDiscipline =
      getAcademicAchievementOfStudentByDiscipline(discipline);
    return academicAchievementByDiscipline.getAchievements();
  }

  /**
   * Получить отметку ученика по дисциплине на дату урока.
   *
   * @param dateOfLesson дата урока
   * @param discipline   дисциплина
   * @return отметка
   */
  public Achievement getAchievementByDisciplineAndLocalDate(Discipline discipline, LocalDate dateOfLesson) {
    AcademicAchievementByDiscipline academicAchievementByDiscipline =
      getAcademicAchievementOfStudentByDiscipline(discipline);
    return academicAchievementByDiscipline.getAchievementByDateOfLesson(dateOfLesson);
  }

  /**
   * Получить объект журнала академической успеваемости студента по конкретной дисциплине.
   *
   * @param discipline дисциплина
   * @return объект журнала академической успеваемости студента по конкретной дисциплине
   */
  public AcademicAchievementByDiscipline getAcademicAchievementOfStudentByDiscipline(Discipline discipline) {
    Optional<AcademicAchievementByDiscipline> academicAchievementByDiscipline =
      this.academicAchievementByDisciplineList.stream()
        .filter(item -> item.getDiscipline().equals(discipline))
        .findFirst();
    if (academicAchievementByDiscipline.isPresent()) {
      return academicAchievementByDiscipline.get();
    } else {
      throw new NotFoundException("Журнала успеваемости ученика по данной дисциплине у ученика нет!");
    }
  }
}
