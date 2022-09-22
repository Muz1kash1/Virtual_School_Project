package com.fabit.schoolapplication.infrastructure.controller.virtualschool.student;

import com.fabit.schoolapplication.application.usecase.virtualschool.student.DeleteStudent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/student")
public class DeleteStudentEndpoint {
  final DeleteStudent deleteStudent;

  /**
   * Endpoint удаления ученика.
   *
   * @param id - идентификатор ученика
   * @return ResponseEntity с OK и телом "Ученик исключен из школы"
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteStudent(@PathVariable long id) {

    log.info("trying to delete student with id: " + id);
    deleteStudent.execute(id);

    return ResponseEntity
        .ok()
        .body("Ученик исключен из школы");
  }
}