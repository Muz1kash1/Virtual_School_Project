package com.fabit.schoolapplication.domain.educatioprogress;

import lombok.Getter;
import lombok.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public final class Mark {
  public static Pattern patternMark =
      java.util.regex.Pattern.compile("^(^[2-5]{1}/[2-5]{1}$|^([2-5]{1})$|^НН/УВ$|^НН$)$");
  private String value;

  private Mark() {
  }

  public static Mark of(String value) {
    Mark mark = new Mark();
    mark.setValue(value);
    return mark;
  }

  /**
   * Валидация строки-значения отметки
   *
   * @param mark строка-значение отметки
   */
  private boolean validate(String mark) {
    Matcher matcherMark = patternMark.matcher(mark);
    return matcherMark.find();
  }

  private void setValue(String value) {
    if (validate(value)) {
      this.value = value;
    } else {
      throw new IllegalArgumentException("Неверный формат отметки");
    }
  }
}
