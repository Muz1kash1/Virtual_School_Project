package com.fabit.schoolapplication.domain.educatioprogress;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Агрегат отметки об успеваемости
 *
 * @author SmirnovMA
 */
@Getter
@EqualsAndHashCode
public final class Mark {
  public static Pattern patternMark =
      java.util.regex.Pattern.compile("^(^[2-5]{1}/[2-5]{1}$|^([2-5]{1})$|^НН/УВ$|^НН$)$");
  private String value;

  private Mark() {}

  public static Mark of(String value) {
    Mark mark = new Mark();
    mark.setValue(value);
    return mark;
  }

  private void setValue(String value) {
    Matcher matcherMark = patternMark.matcher(value);
    if (matcherMark.find()) {
      this.value = value;
    } else {
      throw new IllegalArgumentException("Неверный формат отметки");
    }
  }
}
