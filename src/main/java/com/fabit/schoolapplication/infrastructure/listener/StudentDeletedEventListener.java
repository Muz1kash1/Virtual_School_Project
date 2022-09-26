package com.fabit.schoolapplication.infrastructure.listener;

import com.fabit.schoolapplication.application.usecase.scenarious.schoolclass.GetSchoolClassUseCase;
import com.fabit.schoolapplication.application.usecase.scenarious.schoolclass.RemoveStudentFromClassUseCase;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.event.StudentDeletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
@RequiredArgsConstructor
public class StudentDeletedEventListener {

  private final RemoveStudentFromClassUseCase removeStudentFromSchoolClassUseCase;
  private final GetSchoolClassUseCase getSchoolClassUseCase;

  /**
   * Слушатель события удаления ученика.
   *
   * @param event - событие
   */
  @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
  public void handleStudentDeletedEvent(StudentDeletedEvent event) {
    log.info("Студент под номером " + event.getStudentId() + " был отчислен из школы");

    StudentId studentId = StudentId.of(event.getStudentId());
    SchoolClass schoolClass
        = getSchoolClassUseCase.getByStudentId(studentId.getValue());

    removeStudentFromSchoolClassUseCase
        .execute(SchoolClassId.of(schoolClass.getSchoolClassId().getValue()), studentId);

    log.info("Студент под номером " + event.getStudentId()
        + " автоматически отчислен из класса" + schoolClass);
  }
}