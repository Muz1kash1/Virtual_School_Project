package com.fabit.schoolapplication.infrastructure.listener;

import com.fabit.schoolapplication.infrastructure.usecase.student.event.StudentDeletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
@RequiredArgsConstructor
public class StudentDeletedEventListener {

  @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
  public void handleStudentDeletedEvent(StudentDeletedEvent event) {
    log.info("Студент под номером " + event.getStudentId() + " был отчислен из школы");
    // TO DO Саня допиши удаление его из списка класса
  }
}