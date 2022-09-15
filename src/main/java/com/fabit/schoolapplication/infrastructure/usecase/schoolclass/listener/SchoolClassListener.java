package com.fabit.schoolapplication.infrastructure.usecase.schoolclass.listener;

import com.fabit.schoolapplication.domain.schoolclass.event.SchoolClassAddedStudentEvent;
import com.fabit.schoolapplication.domain.schoolclass.event.SchoolClassCreatedEvent;
import com.fabit.schoolapplication.domain.schoolclass.event.SchoolClassRemovedStudentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SchoolClassListener {

  @EventListener
  public void schoolClassCreated(SchoolClassCreatedEvent event) {
    // todo если потребуется
    log.info("Школьный класс из ивента создан.");
  }

  @EventListener
  public void addedStudentToClass(SchoolClassAddedStudentEvent event) {
    // todo если потребуется
    log.info("Ученик " + event.getContent() + " добавлен в класс.");
  }

  @EventListener
  public void removedStudentToClass(SchoolClassRemovedStudentEvent event) {
    // todo если потребуется
    log.info("Ученик " + event.getContent() + " удалён из класса.");
  }

}
