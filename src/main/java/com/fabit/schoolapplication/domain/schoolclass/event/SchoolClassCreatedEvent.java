package com.fabit.schoolapplication.domain.schoolclass.event;

import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import lombok.ToString;

/**
 * EVENT: создание школьного класса.
 */
@ToString
public class SchoolClassCreatedEvent implements SchoolClassEvent{

  private final SchoolClass schoolClass;

  public SchoolClassCreatedEvent(SchoolClass schoolClass) {
    this.schoolClass = schoolClass;
  }

  /**
   * Получить контент события.
   *
   * @return SchoolClass
   */
  @Override
  public Object getContent() {
    return schoolClass;
  }
}
