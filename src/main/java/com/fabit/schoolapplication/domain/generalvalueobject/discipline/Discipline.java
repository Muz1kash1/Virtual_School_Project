package com.fabit.schoolapplication.domain.generalvalueobject.discipline;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Discipline {

  RUSSIAN_LANGUAGE("Русский язык"),
  LITERATURE("Литература"),
  RUSSIAN_LANGUAGE_AND_LITERATURE("Русский язык и литература"),
  ENGLISH("Английский язык"),
  DEUTSCH("Немецкий язык"),
  FRENCH("Французский язык"),
  MATHS("Математика"),
  ALGEBRA("Алгебра"),
  GEOMETRY("Геометрия"),
  COMPUTING("Информатика"),
  PHYSICS("Физика"),
  CHEMISTRY("Химия"),
  BIOLOGY("Биология"),
  HISTORY("История"),
  SOCIAL_SCIENCE("Обществознание"),
  GEOGRAPHY("География"),
  PHYSICAL_EDUCATION("Физическая культура"),
  MUSIC("Музыка"),
  DRAWING("Рисование"),
  TECHNOLOGY("Труд");

  /**
   * Текстовое представление предмета.
   */
  private final String textView;

  /**
   * Парсинг имени дисциплины в объект Discipline.
   *
   * @param disciplineTextView - текстовое представление дисциплины
   * @return Discipline
   */
  public static Discipline parse(String disciplineTextView) {

    for (Discipline discipline : Discipline.values()) {
      if (discipline.textView.equalsIgnoreCase(disciplineTextView)) {
        return discipline;
      }
    }

    throw new IllegalArgumentException("Дисциплины с названием " + disciplineTextView + " нет");
  }

}
