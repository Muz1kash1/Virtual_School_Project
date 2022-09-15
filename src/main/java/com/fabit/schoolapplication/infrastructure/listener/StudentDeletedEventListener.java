package com.fabit.schoolapplication.infrastructure.listener;

import com.fabit.schoolapplication.infrastructure.usecase.student.event.StudentDeletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class StudentDeletedEventListener implements ApplicationListener<StudentDeletedEvent> {

  @Override
  public void onApplicationEvent(StudentDeletedEvent event) {
    log.info("Студент под номером " + event.getStudentId() + " был отчислен из школы");
    // TO DO Саня допиши удаление его из списка класса
  }
}