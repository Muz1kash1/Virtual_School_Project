package com.fabit.schoolapplication.infrastructure.controller.virtualschool.homeworkforclass;

import com.fabit.schoolapplication.application.usecase.scenarious.homeworkforclass.CreateHomeworkForClass;
import com.fabit.schoolapplication.application.usecase.scenarious.homeworkforclass.GetHomeworkForClass;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.homeworkforclass.dto.HomeworkForClassDto;
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

  /**
   * Метод создающий ДЗ для класса на дисциплину на дату.
   *
   * @param homeworkForClassDto дто содержащее данные урока
   * @return строка с подтверждением успешного создания
   */
  @PostMapping(value = "/homework-for-class", produces = "application/json")
  public ResponseEntity<String> addHomeworkForClass(
      @RequestBody HomeworkForClassDto homeworkForClassDto) {

    createHomeworkForClass.execute(homeworkForClassDto);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body("Домашнее задание задано");
  }

  /**
   * Метод возвращающий ДЗ по его id.
   *
   * @param id идентификатор домашнего задания
   * @return домашнее задание с данным id
   */
  @GetMapping(value = "/homework-for-class/{id}", produces = "application/json")
  public ResponseEntity<HomeworkForClassDto> getHomeworkForClass(@PathVariable long id) {

    return ResponseEntity
        .ok()
        .body(getHomeworkForClass.execute(id));
  }

}
