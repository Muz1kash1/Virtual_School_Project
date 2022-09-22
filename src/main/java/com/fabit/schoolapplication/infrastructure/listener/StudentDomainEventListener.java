package com.fabit.schoolapplication.infrastructure.listener;

import com.fabit.schoolapplication.domain.student.event.StudentChangedInfoEvent;
import com.fabit.schoolapplication.domain.student.event.StudentCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@Slf4j
public class StudentDomainEventListener {

  /**
   * Слушатель события создания студента.
   *
   * @param event - событие
   */
  @EventListener
  public void studentCreatedEvent(StudentCreatedEvent event) {
    log.info("StudentCreatedEvent...");
    log.info(
        "Студент с именем " + event.getStudent().getFullName().getName() + " был успешно создан");
  }

  /**
   * Слушатель события изменения студента.
   *
   * @param event - событие
   */
  @EventListener
  public void studentChangedInfoEvent(StudentChangedInfoEvent event) {
    log.info("StudentChangedInfoEvent...");
    log.info("Студент с именем " + event.getStudent().getFullName() + " был успешно изменен");
  }
}