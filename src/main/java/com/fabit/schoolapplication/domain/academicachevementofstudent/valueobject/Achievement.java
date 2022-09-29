package com.fabit.schoolapplication.domain.academicachevementofstudent.valueobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * Отметка успеваемости ученика.
 *
 * @author SmirnovMA
 */
@Getter
@EqualsAndHashCode
public final class Achievement {

  private static final Pattern PATTERN_VALUE =
    compile("^(^[2-5]{1}/[2-5]{1}$|^([2-5]{1})$|^НН/УВ$|^НН$)$");
  private final LocalDate dateOfLesson;
  private String value;

  public Achievement(LocalDate dateOfLesson, String value) {
    this.dateOfLesson = dateOfLesson;
    this.setValue(value);
  }

  /**
   * Сеттер с валидацией формата оценки.
   *
   * @param value значение оценки
   */
  private void setValue(String value) {
    Matcher matcherValue = PATTERN_VALUE.matcher(value);
    if (matcherValue.find()) {
      this.value = value;
    } else {
      throw new IllegalArgumentException("Неверный формат отметки");
    }
  }
}
