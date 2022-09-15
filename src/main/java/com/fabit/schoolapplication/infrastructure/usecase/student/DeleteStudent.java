package com.fabit.schoolapplication.infrastructure.usecase.student;

import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import com.fabit.schoolapplication.infrastructure.usecase.student.event.StudentDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteStudent {
  final StudentRepository studentRepository;
  final ApplicationEventPublisher eventPublisher;

  public void execute(long id) {
    studentRepository.deleteById(id);
    StudentDeletedEvent event = new StudentDeletedEvent(this, id);
    eventPublisher.publishEvent(event);
  }
}