package com.fabit.schoolapplication.infrastructure.listener;

import com.fabit.schoolapplication.domain.student.event.StudentChangedInfoEvent;
import com.fabit.schoolapplication.domain.student.event.StudentCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@Slf4j
public class StudentDomainEventListener {
  @EventListener
  public void studentCreatedEvent(StudentCreatedEvent event) {
    log.info("StudentCreatedEvent...");
    log.info(
        "Студент с именем " + event.getStudent().getFullName().getName() + " был успешно создан");
  }

  @EventListener
  public void StudentChangedInfoEvent(StudentChangedInfoEvent event) {
    log.info("StudentChangedInfoEvent...");
    log.info("Студент с именем " + event.getStudent().getFullName() + " был успешно изменен");
  }
}