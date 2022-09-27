package com.fabit.schoolapplication.infrastructure.ui.controller.schoolclass;

import com.fabit.schoolapplication.application.usecase.scenario.schoolclass.AddStudentToClassUseCase;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.student.StudentId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AddStudentToClassEndpoint {

  final AddStudentToClassUseCase addStudentToClassUseCase;

  /**
   * Добавление ученика в школьный класс.
   *
   * @param id        - идентификатор школьного класса
   * @param studentId - идентификатор ученика
   * @return ResponseEntity
   */
  @PostMapping(value = "/school-class/{id}")
  public ResponseEntity<?> addStudentToClass(@PathVariable Long id,
                                             @RequestParam Long studentId) {

    addStudentToClassUseCase.execute(SchoolClassId.of(id), StudentId.of(studentId));

    return ResponseEntity
        .status(HttpStatus.NO_CONTENT)
        .build();
  }

}
