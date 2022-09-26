package com.fabit.schoolapplication.infrastructure.controller.virtualschool.schoolclass;

import com.fabit.schoolapplication.application.usecase.scenarious.schoolclass.CreateSchoolClassUseCase;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.schoolclass.dto.SchoolClassDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CreateSchoolClassEndpoint {

  final CreateSchoolClassUseCase createSchoolClassUseCase;

  /**
   * Создание нового школьного класса.
   *
   * @param schoolClassDto - DTO школьного класса
   * @return ResponseEntity
   */
  @PostMapping("/school-class")
  public ResponseEntity<?> createSchoolClass(@RequestBody SchoolClassDto schoolClassDto) {

    SchoolClass schoolClassToCreate = schoolClassDto.toDomain();

    createSchoolClassUseCase.execute(schoolClassToCreate);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .build();
  }

}
