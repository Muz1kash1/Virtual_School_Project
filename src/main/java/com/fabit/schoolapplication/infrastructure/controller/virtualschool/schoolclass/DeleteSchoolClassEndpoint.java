package com.fabit.schoolapplication.infrastructure.controller.virtualschool.schoolclass;

import com.fabit.schoolapplication.application.usecase.scenarious.schoolclass.DeleteSchoolClassUseCase;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.schoolclass.dto.SchoolClassDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DeleteSchoolClassEndpoint {

  final DeleteSchoolClassUseCase deleteSchoolClassUseCase;

  /**
   * Удаление школьного класса.
   *
   * @param schoolClassDto - Dto школьного класса
   * @return ResponseEntity
   */
  @DeleteMapping("/school-class")
  public ResponseEntity<?> deleteSchoolClass(@RequestBody SchoolClassDto schoolClassDto) {

    deleteSchoolClassUseCase.execute(schoolClassDto.toDomain());

    return ResponseEntity
        .status(HttpStatus.NO_CONTENT)
        .build();
  }

}
