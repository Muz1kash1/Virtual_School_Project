package com.fabit.schoolapplication.application.usecase.virtual_school.student;

import com.fabit.schoolapplication.infrastructure.event.StudentDeletedEvent;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Юзкейс удаления ученика.
 */
@Service
@RequiredArgsConstructor
public class DeleteStudent {

  private final StudentRepository studentRepository;
  private final ApplicationEventPublisher eventPublisher;

  /**
   * Удалить ученика с идентификатором id
   *
   * @param id - идентификатор ученика
   */
  public void execute(long id) {
    studentRepository.deleteById(id);
    StudentDeletedEvent event = new StudentDeletedEvent(this, id);
    eventPublisher.publishEvent(event);
  }
}