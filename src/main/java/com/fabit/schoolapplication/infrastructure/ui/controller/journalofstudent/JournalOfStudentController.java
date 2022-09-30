package com.fabit.schoolapplication.infrastructure.ui.controller.journalofstudent;

import com.fabit.schoolapplication.application.usecase.scenario.journalofstudent.JournalByDisciplineScenario;
import com.fabit.schoolapplication.application.usecase.scenario.journalofstudent.JournalOfStudentScenario;
import com.fabit.schoolapplication.application.usecase.scenario.journalofstudent.dto.AchievementForStudentDto;
import com.fabit.schoolapplication.application.usecase.scenario.journalofstudent.dto.AchievementOfStudentDto;
import com.fabit.schoolapplication.application.usecase.scenario.journalofstudent.dto.AllAchievementsOfStudentByDisciplineDto;
import com.fabit.schoolapplication.application.usecase.scenario.journalofstudent.dto.AllDisciplinesOfStudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;

@RestController
@RequestMapping("journal/")
@RequiredArgsConstructor
public class JournalOfStudentController {
  private final JournalOfStudentScenario journalOfStudentScenario;
  private final JournalByDisciplineScenario journalByDisciplineScenario;

  //Создать новый журнал общей академической успеваемости ученика
  @PostMapping("/{studentId}")
  public ResponseEntity<?> createNewJournalForStudent(
    @PathVariable long studentId
  ) {
    journalOfStudentScenario.add(studentId);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  //Удалить журнал общей академической успеваемости ученика
  @DeleteMapping("/{studentId}")
  public ResponseEntity<?> deleteJournalForStudent(
    @PathVariable long studentId
  ) {
    journalOfStudentScenario.remove(studentId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  //Добавить в журнал общей академической успеваемости ученика
  // журнал академической успеваемости по конкретной дисциплине
  @PostMapping("/discipline/{studentId}")
  public ResponseEntity<?> addJournalForStudentByDiscipline(
    @PathVariable long studentId, @RequestParam String discipline
  ) {
    journalByDisciplineScenario.add(studentId, discipline);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  //Удалить из журнала общей академической успеваемости ученика
  // журнал академической по конкретной дисциплине
  @DeleteMapping("discipline/{studentId}/")
  public ResponseEntity<?> deleteJournalForStudentByDiscipline(
    @PathVariable long studentId, @RequestParam String discipline
  ) {
    journalByDisciplineScenario.remove(studentId, discipline);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  //Получить все доступные ученику дисциплины
  @GetMapping("discipline/{studentId}/")
  public ResponseEntity<AllDisciplinesOfStudentDto> getAllJournalsForStudentByDiscipline(
    @PathVariable long studentId
  ) {
    return ResponseEntity.status(HttpStatus.OK).body(
      journalOfStudentScenario.getAllDisciplinesForStudent(studentId)
    );
  }

  // Получить все отметки об успеваемости ученика по данной дисциплине
  @GetMapping("discipline/achievement/{studentId}/")
  public ResponseEntity<AllAchievementsOfStudentByDisciplineDto> getJournalForStudentByDiscipline(
    @PathVariable long studentId, @RequestParam String discipline
  ) {
    return ResponseEntity.status(HttpStatus.OK).body(
      journalByDisciplineScenario.getAllAchievementsByDiscipline(
        studentId, discipline
      )
    );
  }

  // Получить отметку об успеваемости ученика по данной дисциплине по дате урока
  @GetMapping("discipline/achievement/{studentId}/{dateOfLesson}/")
  public ResponseEntity<AchievementOfStudentDto> getJournalForStudentByDiscipline(
    @PathVariable long studentId,
    @PathVariable LocalDate dateOfLesson,
    @RequestParam String discipline
  ) {
    return ResponseEntity.status(HttpStatus.OK).body(
      journalByDisciplineScenario.getAchievementByDisciplineAndDateOfLesson(
        studentId, discipline, dateOfLesson
      )
    );
  }

  //Добавить отметку об успеваемости ученику
  @PostMapping("discipline/achievement/{studentId}/")
  public ResponseEntity<?> addAchievementForStudent(
    @PathVariable long studentId,
    @RequestBody AchievementForStudentDto achievement
  ) {
    journalByDisciplineScenario.addAchievement(studentId, achievement);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  //Изменить отметку об успеваемости ученику
  @PutMapping("discipline/achievement/{studentId}/")
  public ResponseEntity<?> changeAchievementForStudent(
    @PathVariable long studentId,
    @RequestBody AchievementForStudentDto achievement
  ) {
    journalByDisciplineScenario.changeAchievement(studentId, achievement);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  //Удалить отметку об успеваемости ученику
  @PatchMapping("discipline/achievement/{studentId}/")
  public ResponseEntity<?> deleteAchievementForStudent(
    @PathVariable long studentId,
    @RequestBody AchievementForStudentDto achievement
  ) {
    journalByDisciplineScenario.removeAchievement(studentId, achievement);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
