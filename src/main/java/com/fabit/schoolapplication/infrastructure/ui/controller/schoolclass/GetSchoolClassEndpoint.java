package com.fabit.schoolapplication.infrastructure.ui.controller.schoolclass;

import com.fabit.schoolapplication.application.usecase.scenario.schoolclass.GetSchoolClassUseCase;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
import com.fabit.schoolapplication.infrastructure.ui.controller.schoolclass.dto.SchoolClassDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

@RequiredArgsConstructor
@RestController
public class GetSchoolClassEndpoint {

  final GetSchoolClassUseCase getSchoolClassUseCase;

  /**
   * Получение списка всех школьных классов школы.
   *
   * @return ResponseEntity with SchoolClassDto
   */
  @GetMapping(value = "/school-class", produces = "application/json")
  public ResponseEntity<List<SchoolClassDto>> getAllSchoolClasses() {

    List<SchoolClass> listOfDomainClasses = getSchoolClassUseCase.all();
    List<SchoolClassDto> listOfDtoClasses = new ArrayList<>(listOfDomainClasses.size());

    listOfDomainClasses.forEach(
        domainClass -> listOfDtoClasses.add(SchoolClassDto.of(domainClass))
    );

    return ResponseEntity
        .ok()
        .body(listOfDtoClasses);
  }

  /**
   * Получение школьного класса с указанным в path идентификатором.
   *
   * @param id - идентификатор
   * @return ResponseEntity with SchoolClassDto
   */
  @GetMapping(value = "/school-class/{id}", produces = "application/json")
  public ResponseEntity<SchoolClassDto> getSchoolClassById(@PathVariable Long id) {

    SchoolClass domainClass;
    try {
      domainClass = getSchoolClassUseCase.byId(id);
    } catch (NotFoundException e) {
      throw new NotFoundException("Школьный класс  (id" + id + ") не найден.");
    }

    return ResponseEntity
        .ok()
        .body(SchoolClassDto.of(domainClass));
  }

  /**
   * Получение школьного класса с указанным именем (параллель + литера).
   *
   * @param dto - объект школьного класса json, содержащий параллель и литеру (DTO)
   * @return ResponseEntity with SchoolClassDto
   */
  @GetMapping(value = "/school-class", consumes = "application/json")
  public ResponseEntity<SchoolClassDto> getSchoolClassByName(@RequestBody SchoolClassDto dto) {
    return ResponseEntity.ok()
        .body(
            SchoolClassDto.of(
                getSchoolClassUseCase.byName(
                    SchoolClassName.of(dto.getParallel(), dto.getLitera()))
            )
        );
  }

  /**
   * Получение школьного класса по идентификатору студента.
   *
   * @param studentId - идентификатор студента
   * @return ResponseEntity with SchoolClassDto
   */
  @GetMapping("/school-class/student/{studentId}")
  public ResponseEntity<SchoolClassDto> getSchoolClassByStudentId(@PathVariable long studentId) {
    return ResponseEntity
        .ok()
        .body(SchoolClassDto.of(getSchoolClassUseCase.getByStudentId(studentId)));
  }
}
