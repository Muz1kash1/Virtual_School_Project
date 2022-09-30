package com.fabit.schoolapplication.domain.journalofstudent.entity;

import com.fabit.schoolapplication.domain.generalvalueobject.discipline.Discipline;
import com.fabit.schoolapplication.domain.journalofstudent.id.JournalByDisciplineId;
import com.fabit.schoolapplication.domain.journalofstudent.valueobject.Achievement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Сущность успеваемости ученика по конкретной дисциплине.
 *
 * @author SmirnovMA
 */
@Getter
@EqualsAndHashCode
public class JournalByDiscipline {

  private JournalByDisciplineId id;

  private Discipline discipline;
  private List<Achievement> achievements;

  private JournalByDiscipline() {
  }

  private JournalByDiscipline(
    JournalByDisciplineId id, Discipline discipline
  ) {

    this.id = id;
    this.discipline = discipline;
    this.achievements = new ArrayList<>();
  }

  /**
   * Статическая фабрика.
   *
   * @param id         id объекта журнала академической успеваемости студента по конкретной дисциплине.
   * @param discipline дисциплина
   * @return объект журнала академической успеваемости студента по конкретной дисциплине
   */
  public static JournalByDiscipline of(
    JournalByDisciplineId id,
    Discipline discipline
  ) {

    return new JournalByDiscipline(id, discipline);
  }

  /**
   * Метод получения копии объекта журнала академической успеваемости студента по конкретной дисциплине.
   *
   * @param id           id объекта журнала академической успеваемости студента по конкретной дисциплине
   * @param discipline   дисциплина
   * @param achievements список отметок
   * @return объект журнала академической успеваемости студента по конкретной дисциплине
   */
  public static JournalByDiscipline copyOf(
    JournalByDisciplineId id,
    Discipline discipline,
    List<Achievement> achievements
  ) {

    JournalByDiscipline journalByDiscipline
      = new JournalByDiscipline();

    journalByDiscipline.id = id;
    journalByDiscipline.discipline = discipline;
    journalByDiscipline.achievements = achievements;

    return journalByDiscipline;
  }

  /**
   * Поставить отметку об успеваемости.
   *
   * @param value        строковое представление оценки
   * @param dateOfLesson дата урока
   */
  public void addAchievement(
    String value, LocalDate dateOfLesson
  ) {

    achievements.add(
      new Achievement(dateOfLesson, value)
    );
  }

  /**
   * Изменить отметку об успеваемости.
   *
   * @param value        строковое представление оценки
   * @param dateOfLesson дата урока
   */
  public void changeAchievement(
    String value, LocalDate dateOfLesson
  ) {

    Achievement achievement
      = getAchievementByDateOfLesson(dateOfLesson);

    if (achievement.getDateOfLesson().equals(dateOfLesson)) {
      achievements.remove(achievement);
      achievements.add(
        new Achievement(dateOfLesson, value)
      );
    }
  }

  /**
   * Получить оценку на дату урока.
   *
   * @param dateOfLesson дата урока
   * @return объект отметки об успеваемости
   */
  public Achievement getAchievementByDateOfLesson(
    LocalDate dateOfLesson
  ) {

    Optional<Achievement> achievement
      = achievements
      .stream()
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
