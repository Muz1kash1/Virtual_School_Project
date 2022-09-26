package com.fabit.schoolapplication.infrastructure.listener;

import com.fabit.schoolapplication.application.usecase.scenarious.schoolclass.GetSchoolClassByStudentIdIn;
import com.fabit.schoolapplication.application.usecase.scenarious.schoolclass.RemoveStudentFromSchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.event.StudentDeletedEvent;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.SchoolClassEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
@RequiredArgsConstructor
public class StudentDeletedEventListener {

  private final RemoveStudentFromSchoolClass removeStudentFromSchoolClass;
  private final GetSchoolClassByStudentIdIn getSchoolClassByStudentIdIn;

  /**
   * Слушатель события удаления ученика.
   *
   * @param event - событие
   */
  @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
  public void handleStudentDeletedEvent(StudentDeletedEvent event) {
    log.info("Студент под номером " + event.getStudentId() + " был отчислен из школы");

    StudentId studentId = StudentId.of(event.getStudentId());
    SchoolClassEntity schoolClassEntity = getSchoolClassByStudentIdIn.execute(studentId);

    removeStudentFromSchoolClass.execute(SchoolClassId.of(schoolClassEntity.getId()), studentId);

    log.info("Студент под номером " + event.getStudentId()
        + " автоматически отчислен из класса" + schoolClassEntity);
  }
}