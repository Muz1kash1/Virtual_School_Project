package com.fabit.schoolapplication.domain.schoolclass.event;

/**
 * Интерфейс доменных ивентов школьного класса.
 */
public interface SchoolClassDomainEvent {

  /**
   * Метод получения содержимого (контента) ивента.
   *
   * @return Object
   */
  Object getContent();
}
