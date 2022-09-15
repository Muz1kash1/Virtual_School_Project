package com.fabit.schoolapplication.infrastructure.controller.schoolclass;

import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.SchoolClassEntity;
import com.fabit.schoolapplication.infrastructure.usecase.schoolclass.AddStudentToSchoolClass;
import com.fabit.schoolapplication.infrastructure.usecase.schoolclass.CreateSchoolClass;
import com.fabit.schoolapplication.infrastructure.usecase.schoolclass.DeleteSchoolClass;
import com.fabit.schoolapplication.infrastructure.usecase.schoolclass.GetSchoolClass;
import com.fabit.schoolapplication.infrastructure.usecase.schoolclass.RemoveStudentFromSchoolClass;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SchoolClassController {

  private final CreateSchoolClass createSchoolClass;
  private final AddStudentToSchoolClass addStudentToSchoolClass;
  private final RemoveStudentFromSchoolClass removeStudentFromSchoolClass;
  private final DeleteSchoolClass deleteSchoolClass;
  private final GetSchoolClass getSchoolClass;

  // -------
  // ** GetSchoolClass usecase

  /**
   * Получение списка всех школьных классов школы.
   *
   * @return ResponseEntity<List < SchoolClassEntity>>
   */
  @GetMapping(value = "/school-class", produces = "application/json")
  public ResponseEntity<List<SchoolClassEntity>> getAllSchoolClasses() {
    return ResponseEntity.ok().body(getSchoolClass.all());
  }

  /**
   * Получение школьного класса с указанным в path идентификатором.
   *
   * @param id - идентификатор
   * @return ResponseEntity<SchoolClassEntity>
   */
  @GetMapping(value = "/school-class/{id}", produces = "application/json")
  public ResponseEntity<SchoolClassEntity> getSchoolClassById(@PathVariable Long id) {
    return ResponseEntity.ok().body(getSchoolClass.byId(id));
  }

  /**
   * Получение школьного класса с указанным именем (параллель + литера).
   *
   * @param dto - объект школьного класса json, содержащий параллель и литеру (DTO)
   * @return ResponseEntity<SchoolClassEntity>
   */
  @GetMapping(value = "/school-class", consumes = "application/json")
  public ResponseEntity<SchoolClassEntity> getSchoolClassByName(@RequestBody SchoolClassDTO dto) {
    return ResponseEntity.ok().body(getSchoolClass.byName(dto.getParallel(), dto.getLitera()));
  }

  // -------
  // ** other usecase

  /**
   * Создание нового школьного класса.
   *
   * @param schoolClassDTO - DTO школьного класса
   * @return ResponseEntity
   */
  @PostMapping("/school-class")
  public ResponseEntity<?> createSchoolClass(
      @RequestBody SchoolClassDTO schoolClassDTO
  ) {
    createSchoolClass.execute(schoolClassDTO.toDomain());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  /**
   * Удаление школьного класса.
   *
   * @param schoolClassDTO - DTO школьного класса
   * @return ResponseEntity
   */
  @DeleteMapping("/school-class")
  public ResponseEntity<?> deleteSchoolClass(
      @RequestBody SchoolClassDTO schoolClassDTO
  ) {
    deleteSchoolClass.execute(schoolClassDTO.toDomain());
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  /**
   * Добавление ученика в школьный класс.
   *
   * @param schoolClassId - идентификатор школьного класса
   * @param studentId     - идентификатор ученика
   * @return ResponseEntity
   */
  @PostMapping(value = "/school-class/{schoolClassId}")
  public ResponseEntity<?> addStudentToClass(
      @PathVariable Long schoolClassId, @RequestParam Long studentId) {
    addStudentToSchoolClass.execute(SchoolClassId.of(schoolClassId), StudentId.of(studentId));
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  /**
   * Удаление ученика из указанного класса.
   *
   * @param schoolClassId - идентификатор школьного класса
   * @param studentId     - идентификатор ученика
   * @return ResponseEntity
   */
  @DeleteMapping("/school-class/{schoolClassId}")
  public ResponseEntity<?> removeStudentFromClass(
      @PathVariable Long schoolClassId, @RequestParam Long studentId) {
    removeStudentFromSchoolClass.execute(SchoolClassId.of(schoolClassId), StudentId.of(studentId));
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
