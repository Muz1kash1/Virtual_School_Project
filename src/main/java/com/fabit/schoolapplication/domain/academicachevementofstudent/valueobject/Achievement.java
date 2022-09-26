package com.fabit.schoolapplication.domain.academicachevementofstudent.valueobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Отметка успеваемости ученика
 *
 * @author SmirnovMA
 */
@Getter
@EqualsAndHashCode
public final class Achievement {
  public static Pattern patternValue =
    java.util.regex.Pattern.compile("^(^[2-5]{1}/[2-5]{1}$|^([2-5]{1})$|^НН/УВ$|^НН$)$");
  private LocalDate dateOfLesson;
  private String value;

  private Achievement() {
  }

  public Achievement(
    LocalDate dateOfLesson,
    String value) {
    this.dateOfLesson = dateOfLesson;
    this.setValue(value);
  }

  /**
   * Сеттер с валидацией формата оценки.
   *
   * @param value значение оценки
   */
  private void setValue(String value) {
    Matcher matcherValue = patternValue.matcher(value);
    if (matcherValue.find()) {
      this.value = value;
    } else {
      throw new IllegalArgumentException("Неверный формат отметки");
    }
  }
}
