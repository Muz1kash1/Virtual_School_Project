package com.fabit.schoolapplication.domain.generalvalueobject.snils;

import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class Snils {

  String numberView;

  private Snils(String numberView) {
    this.numberView = numberView;
  }

  /**
   * Factory method - Создание Snils.
   *
   * @param numberView - номер СНИЛС
   * @return Snils
   */
  public static Snils of(String numberView) {
    if (isValidNumberView(numberView)) {
      return new Snils(numberView);
    } else {
      throw new IllegalArgumentException();
    }
  }

  private static boolean isValidNumberView(String numberView) {
    return Pattern.matches("^\\d{3}-\\d{3}-\\d{3}-\\d{2}$", numberView);
  }

  @Override
  public String toString() {
    return numberView;
  }
}