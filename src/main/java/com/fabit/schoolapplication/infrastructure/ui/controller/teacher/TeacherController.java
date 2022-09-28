package com.fabit.schoolapplication.infrastructure.ui.controller.teacher;

import com.fabit.schoolapplication.application.usecase.scenario.teacher.CreateTeacher;
import com.fabit.schoolapplication.application.usecase.scenario.teacher.DeleteTeacher;
import com.fabit.schoolapplication.application.usecase.scenario.teacher.EditTeacher;
import com.fabit.schoolapplication.application.usecase.scenario.teacher.GetTeacher;
import com.fabit.schoolapplication.domain.teacher.Teacher;
import com.fabit.schoolapplication.infrastructure.ui.controller.mapper.TeacherControllerMapper;
import com.fabit.schoolapplication.infrastructure.ui.controller.teacher.dto.DeactivateDto;
import com.fabit.schoolapplication.infrastructure.ui.controller.teacher.dto.TeacherDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {
  private final CreateTeacher createTeacher;
  private final EditTeacher editTeacher;
  private final GetTeacher getTeacher;
  private final DeleteTeacher deleteTeacher;

  private final TeacherControllerMapper teacherControllerMapper;

  /**
   * Endpoint создания учителя и добавления его в базу данных.
   *
   * @param teacherDto - ДТО учителя
   * @return ResponseEntity with status CREATED и созданным учителем
   */
  @PostMapping
  public ResponseEntity<Teacher> createTeacher(@RequestBody TeacherDto teacherDto) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(createTeacher.execute(teacherControllerMapper.mapDtoToDomain(teacherDto)));
  }

  /**
   * Endpoint активации учителя.
   *
   * @param teacherId - идентификатор учителя
   * @return ResponseEntity with status ACCEPTED и учителем
   */
  @PutMapping("/{teacherId}/activate")
  public ResponseEntity<Teacher> activateTeacher(@PathVariable long teacherId) {
    return ResponseEntity
      .status(HttpStatus.ACCEPTED)
      .body(editTeacher.activate(teacherId));
  }

  /**
   * Endpoint деактивации учителя (больничный, отпуск).
   *
   * @param deactivateDto - деактивирующее ДТО
   * @return ResponseEntity with status ACCEPTED и учителем
   */
  @PutMapping("/deactivate")
  public ResponseEntity<Teacher> deactivateTeacher(@RequestBody DeactivateDto deactivateDto) {
    return ResponseEntity
      .status(HttpStatus.ACCEPTED)
      .body(editTeacher.deactivate(deactivateDto.getTeacherId(),
        deactivateDto.getFrom(),
        deactivateDto.getTo())
      );
  }

  /**
   * Endpoint получения учителя по идентификатору.
   *
   * @param teacherId - идентификатор учителя
   * @return ResponseEntity with status OK и учителем
   */
  @GetMapping("/{teacherId}")
  public ResponseEntity<Teacher> getTeacher(@PathVariable long teacherId) {
    return ResponseEntity
      .ok()
      .body(getTeacher.byId(teacherId));
  }

  /**
   * Endpoint получения всех учителей.
   *
   * @return ResponseEntity with status OK и списком учителей
   */
  @GetMapping
  public ResponseEntity<List<Teacher>> getAllTeachers() {
    return ResponseEntity
      .ok()
      .body(getTeacher.all());
  }

  /**
   * Endpoint удаления учителя.
   *
   * @param teacherId - идентификатор учителя
   * @return ResponseEntity with status NO_CONTENT
   */
  @DeleteMapping("/{teacherId}")
  public ResponseEntity<?> deleteTeacher(@PathVariable long teacherId) {

    deleteTeacher.execute(teacherId);

    return ResponseEntity
      .status(HttpStatus.NO_CONTENT)
      .build();
  }
}