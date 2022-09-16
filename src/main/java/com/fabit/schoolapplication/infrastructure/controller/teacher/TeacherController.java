package com.fabit.schoolapplication.infrastructure.controller.teacher;

import com.fabit.schoolapplication.infrastructure.controller.teacher.dto.DeactivateDto;
import com.fabit.schoolapplication.infrastructure.controller.teacher.dto.StandingYearsDto;
import com.fabit.schoolapplication.infrastructure.controller.teacher.dto.TeacherDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.usecase.teacher.CreateTeacher;
import com.fabit.schoolapplication.infrastructure.usecase.teacher.DeleteTeacher;
import com.fabit.schoolapplication.infrastructure.usecase.teacher.EditTeacher;
import com.fabit.schoolapplication.infrastructure.usecase.teacher.GetTeacher;
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

  @PostMapping
  public ResponseEntity<TeacherEntity> createTeacher(@RequestBody TeacherDto teacherDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(createTeacher.execute(teacherDto));
  }

  @PutMapping("standing-years/")
  public ResponseEntity<TeacherEntity> changeStandingYears(@RequestBody StandingYearsDto standingYearsDto) {
    return ResponseEntity.status(HttpStatus.ACCEPTED)
        .body(editTeacher.changeStandingYears(standingYearsDto));
  }

  @PutMapping("/{teacherId}/activate")
  public ResponseEntity<TeacherEntity> activateTeacher(@PathVariable long teacherId) {
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(editTeacher.activate(teacherId));
  }

  @PutMapping("/deactivate")
  public ResponseEntity<TeacherEntity> deactivateTeacher(@RequestBody DeactivateDto deactivateDto) {
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(editTeacher.deactivate(deactivateDto));
  }

  @GetMapping("{teacherId}")
  public ResponseEntity<TeacherEntity> getTeacher(@PathVariable long teacherId) {
    return ResponseEntity.ok().body(getTeacher.byId(teacherId));
  }

  @GetMapping
  public ResponseEntity<List<TeacherEntity>> getAllTeachers() {
    return ResponseEntity.ok().body(getTeacher.all());
  }

  @DeleteMapping("{teacherId}")
  public ResponseEntity<?> deleteTeacher(@PathVariable long teacherId) {
    deleteTeacher.execute(teacherId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}