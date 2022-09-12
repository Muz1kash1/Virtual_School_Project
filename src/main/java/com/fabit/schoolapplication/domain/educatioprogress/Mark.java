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
      java.util.regex.Pattern.compile("^(^[2-5]{1}/[2-5]{1}$|^([2-5]{1})$)$");
  private int firstValue;

  private int secondValue;

  private Mark() {}

  public static Mark parse(String value) {
    Mark mark = new Mark();
    mark.setString(value);
    return mark;
  }

  public static Mark of(int firstValue) {
    Mark mark = new Mark();
    mark.setFirstValue(firstValue);
    return mark;
  }

  public static Mark of(int firstValue, int secondValue) {
    Mark mark = new Mark();
    mark.setFirstValue(firstValue);
    mark.setSecondValue(secondValue);
    return mark;
  }

  private void setString(String value) {
    Matcher matcherMark = patternMark.matcher(value);
    if (matcherMark.find()) {
      String[] arr = value.split("/");
      this.firstValue = Integer.parseInt(arr[0]);
      if (arr.length > 1) {
        this.secondValue = Integer.parseInt(arr[1]);
      }
    } else {
      throw new IllegalArgumentException("Неверный формат отметки");
    }
  }

  public String getValue() {
    if (secondValue != 0) {
      return String.format("%d/%d", firstValue, secondValue);
    } else {
      return String.valueOf(firstValue);
    }
  }

  private void setFirstValue(int firstValue) {
    if (firstValue >= 2 && firstValue <= 5) {
      this.firstValue = firstValue;
    }
  }

  private void setSecondValue(int secondValue) {
    if (secondValue >= 2 && secondValue <= 5) {
      this.secondValue = secondValue;
    }
  }
}
