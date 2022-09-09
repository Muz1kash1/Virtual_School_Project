package com.fabit.schoolapplication.domain;

import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class Snils {

  String numberView;

  private Snils(String numberView) {
    this.numberView = numberView;
  }

  public static Snils of(String numberView) {
    return new Snils(numberView);
  }

}
