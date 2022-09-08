package com.fabit.schoolapplication.domain.educatioprogress;

import lombok.Getter;
import lombok.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Value
public class Mark {
  public static Pattern patternMark =
      java.util.regex.Pattern.compile("^(^[2-5]{1}/[2-5]{1}$|^([2-5]{1})$|^НН/УВ$|^НН$)$");
  String value;

  public Mark(String value) {
    validate(value);
    this.value = value;
  }

  /**
   * Валидация строки-значения отметки
   * @param mark строка-значение отметки
   */
  public void validate(String mark) {
    Matcher matcherMark = patternMark.matcher(mark);
    if (!matcherMark.find()) {
      throw new IllegalArgumentException("Неверный формат отметки");
    }
  }
}
