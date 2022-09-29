package com.fabit.schoolapplication.domain.academicachevementofstudent.entity;

import com.fabit.schoolapplication.domain.academicachevementofstudent.id.AcademicAchievementByDisciplineId;
import com.fabit.schoolapplication.domain.academicachevementofstudent.valueobject.Achievement;
import com.fabit.schoolapplication.domain.generalvalueobject.discipline.Discipline;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Сущность успеваемости ученика по конкретной дисциплине.
 *
 * @author SmirnovMA
 */
@Getter
@EqualsAndHashCode
public class AcademicAchievementByDiscipline {

  private AcademicAchievementByDisciplineId id;
  private Discipline discipline;
  private List<Achievement> achievements;

  private AcademicAchievementByDiscipline() {
  }

  private AcademicAchievementByDiscipline(AcademicAchievementByDisciplineId id,
                                          Discipline discipline) {

    this.id = id;
    this.discipline = discipline;
    this.achievements = new ArrayList<>();
  }

  /**
   * Статическая фабрика.
   *
   * @param id         id объекта журнала академической успеваемости студента по конкретной
   *                   дисциплине.
   * @param discipline дисциплина
   * @return объект журнала академической успеваемости студента по конкретной дисциплине
   */
  public static AcademicAchievementByDiscipline of(AcademicAchievementByDisciplineId id,
                                                   Discipline discipline) {

    return new AcademicAchievementByDiscipline(id, discipline);
  }

  /**
   * Метод получения копии объекта журнала академической успеваемости студента по конкретной
   * дисциплине.
   *
   * @param id           id объекта журнала академической успеваемости студента по конкретной
   *                     дисциплине
   * @param discipline   дисциплина
   * @param achievements список отметок
   * @return объект журнала академической успеваемости студента по конкретной дисциплине
   */
  public static AcademicAchievementByDiscipline copyOf(AcademicAchievementByDisciplineId id,
                                                       Discipline discipline,
                                                       List<Achievement> achievements) {

    AcademicAchievementByDiscipline academicAchievementByDiscipline
        = new AcademicAchievementByDiscipline();

    academicAchievementByDiscipline.id = id;
    academicAchievementByDiscipline.discipline = discipline;
    academicAchievementByDiscipline.achievements = achievements;

    return academicAchievementByDiscipline;
  }

  /**
   * Поставить отметку об успеваемости.
   *
   * @param value        строковое представление оценки
   * @param dateOfLesson дата урока
   */
  public void addAchievement(String value, LocalDate dateOfLesson) {
    this.achievements.add(new Achievement(dateOfLesson, value));
  }

  /**
   * Изменить отметку об успеваемости.
   *
   * @param value        строковое представление оценки
   * @param dateOfLesson дата урока
   */
  public void changeAchievement(String value, LocalDate dateOfLesson) {
    Achievement achievement = getAchievementByDateOfLesson(dateOfLesson);

    if (achievement.getDateOfLesson().equals(dateOfLesson)) {
      this.achievements.remove(achievement);
      this.achievements.add(new Achievement(dateOfLesson, value));
    }
  }

  /**
   * Получить оценку на дату урока.
   *
   * @param dateOfLesson дата урока
   * @return объект отметки об успеваемости
   */
  public Achievement getAchievementByDateOfLesson(LocalDate dateOfLesson) {

    Optional<Achievement> achievement = this.achievements.stream()
        .filter(item -> item.getDateOfLesson().equals(dateOfLesson))
        .findFirst();

    if (achievement.isPresent()) {
      return achievement.get();
    } else {
      throw new IllegalArgumentException(
          "Оценки на данную дату урока по данной дисциплине у ученика нет!"
      );
    }
  }
}
