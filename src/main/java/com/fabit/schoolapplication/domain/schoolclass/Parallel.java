package com.fabit.schoolapplication.domain.schoolclass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class Parallel {

  int grade;
  String litera;
  String textView;

  private Parallel(int grade, String litera) {
    this.grade = grade;
    this.litera = litera;
    this.textView = grade + "" + litera;
  }

  // -------
  // ** Фабричный метод

  /**
   * Фабричный метод для создания экземпляра параллели
   *
   * @param grade  год обучения (1-11)
   * @param litera литера (А-Я без Ъ и Ь)
   * @return Parallel
   */
  public static Parallel create(int grade, String litera) {
    if (Parallel.isValid(grade, litera)) {
      return new Parallel(grade, litera);
    } else {
      throw new IllegalArgumentException("Неверный формат параллели");
    }


  }

  // -------
  // ** Валидация

  /**
   * Валидация Parallel. Возвращает true, если данные для создания параллели валидны.
   *
   * @param grade  год обучения (1-11)
   * @param litera литера (А-Я без Ъ и Ь)
   * @return boolean
   */
  public static boolean isValid(int grade, String litera) {
    Pattern patternMark = Pattern.compile("(?![ЬЪ])[А-Я]");
    Matcher literaMatcher = patternMark.matcher(litera);

    boolean isGradeValid = (grade <= 11 && grade >= 1);
    boolean isLiteraValid = literaMatcher.find();

    return (isGradeValid && isLiteraValid);
  }

}
