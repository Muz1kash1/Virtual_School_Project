package com.fabit.schoolapplication.infrastructure.ui.controller.schoolclass;

import com.fabit.schoolapplication.application.usecase.scenario.schoolclass.RemoveStudentFromClassUseCase;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.student.StudentId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RemoveStudentFromClassEndpoint {

  final RemoveStudentFromClassUseCase removeStudentFromClassUseCase;

  /**
   * Удаление ученика из указанного класса.
   *
   * @param id        - идентификатор школьного класса
   * @param studentId - идентификатор ученика
   * @return ResponseEntity
   */
  @DeleteMapping("/school-class/{id}")
  public ResponseEntity<?> removeStudentFromClass(
      @PathVariable Long id, @RequestParam Long studentId) {

    removeStudentFromClassUseCase.execute(SchoolClassId.of(id), StudentId.of(studentId));

    return ResponseEntity
        .status(HttpStatus.NO_CONTENT)
        .build();
  }

}
