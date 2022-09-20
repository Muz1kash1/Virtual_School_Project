package com.fabit.schoolapplication.domain.schoolclass.event;

import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import lombok.ToString;

/**
 * EVENT: создание школьного класса.
 */
@ToString
public class SchoolClassCreatedDomainEvent implements SchoolClassDomainEvent {

  private final SchoolClass schoolClass;

  public SchoolClassCreatedDomainEvent(SchoolClass schoolClass) {
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
