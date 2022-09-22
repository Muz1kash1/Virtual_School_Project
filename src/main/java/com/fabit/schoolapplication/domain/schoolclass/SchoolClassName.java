package com.fabit.schoolapplication.domain.schoolclass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class SchoolClassName {

  int parallel;
  String litera;

  /**
   * Геттер для получения полного названия (11Б, 8А, 7В).
   *
   * @return String полного имени
   */
  public String getFullName() {
    return parallel + "" + litera;
  }

  // -------
  // ** Приватный конструктор

  private SchoolClassName(int parallel, String litera) {
    this.parallel = parallel;
    this.litera = litera;
  }

  // -------
  // ** Фабричный метод

  /**
   * Фабричный метод для создания экземпляра названия школьного класса.
   *
   * @param parallel год обучения (1-11)
   * @param litera   литера (А-Я без Ъ и Ь)
   * @return Parallel
   */
  public static SchoolClassName of(int parallel, String litera) {

    if (SchoolClassName.isValid(parallel, litera)) {
      return new SchoolClassName(parallel, litera);
    } else {
      throw new IllegalArgumentException("Неверный формат данных для создания названия класса");
    }

  }

  // -------
  // ** Валидация

  /**
   * Валидация Parallel.
   * Возвращает true, если данные для создания названия школьного класса валидны.
   *
   * @param parallel - год обучения (1-11)
   * @param litera   - литера (А-Я без Ъ и Ь)
   * @return boolean
   */
  private static boolean isValid(int parallel, String litera) {

    Pattern patternMark = Pattern.compile("(?![ЬЪ])[А-Я]");
    Matcher literaMatcher = patternMark.matcher(litera);

    boolean isGradeValid = (parallel <= 11 && parallel >= 1);
    boolean isLiteraValid = literaMatcher.find();

    return (isGradeValid && isLiteraValid);
  }

}
