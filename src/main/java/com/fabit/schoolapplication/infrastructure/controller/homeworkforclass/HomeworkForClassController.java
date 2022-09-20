package com.fabit.schoolapplication.infrastructure.controller.homeworkforclass;

import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.infrastructure.controller.homeworkforclass.dto.HomeworkForClassDto;
import com.fabit.schoolapplication.infrastructure.usecase.homeworkforclass.CreateHomeworkForClass;
import com.fabit.schoolapplication.infrastructure.usecase.homeworkforclass.GetHomeworkForClass;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HomeworkForClassController {

  final CreateHomeworkForClass createHomeworkForClass;
  final GetHomeworkForClass getHomeworkForClass;

  @PostMapping(value = "/HomeworkForClass", produces = "application/json")
  public ResponseEntity<String> addHomeworkForClass(
      @RequestBody HomeworkForClassDto homeworkForClassDto) {
    createHomeworkForClass.execute(homeworkForClassDto);
    return ResponseEntity.status(HttpStatus.CREATED).body("Домашнее задание задано");
  }

  @GetMapping(value = "/HomeworkForClass/{id}", produces = "application/json")
  public ResponseEntity<HomeworkForClassDto> getHomeworkForClass(@PathVariable long id) {
    return ResponseEntity.ok().body(getHomeworkForClass.execute(id));
  }

}
