package com.fabit.schoolapplication.infrastructure.listener;

import com.fabit.schoolapplication.domain.schoolclass.event.SchoolClassAddedStudentDomainEvent;
import com.fabit.schoolapplication.domain.schoolclass.event.SchoolClassCreatedDomainEvent;
import com.fabit.schoolapplication.domain.schoolclass.event.SchoolClassRemovedStudentDomainEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SchoolClassEventListener {

  @EventListener
  public void schoolClassCreated(SchoolClassCreatedDomainEvent event) {
    log.info("Школьный класс из ивента создан. " + event);
  }

  @EventListener
  public void addedStudentToClass(SchoolClassAddedStudentDomainEvent event) {
    log.info("Ученик " + event.getContent() + " добавлен в класс.");
  }

  @EventListener
  public void removedStudentToClass(SchoolClassRemovedStudentDomainEvent event) {
    log.info("Ученик " + event.getContent() + " удалён из класса.");
  }

}
