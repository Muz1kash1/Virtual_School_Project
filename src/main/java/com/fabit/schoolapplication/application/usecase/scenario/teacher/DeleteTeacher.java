package com.fabit.schoolapplication.application.usecase.scenario.teacher;

import com.fabit.schoolapplication.infrastructure.event.TeacherDeletedEvent;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeleteTeacher {
  private final TeacherRepository teacherRepository;
  private final ApplicationEventPublisher eventPublisher;

  /**
   * Удалить учителя по идентификатору.
   *
   * @param teacherId - идентификатор учителя
   */
  @Transactional
  public void execute(long teacherId) {
    teacherRepository.deleteById(teacherId);
    log.info("Учитель (id" + teacherId + ") удалён из БД.");

    TeacherDeletedEvent event = new TeacherDeletedEvent(this, teacherId);
    eventPublisher.publishEvent(event);
    log.info("EVENT: " + event);

  }
}
