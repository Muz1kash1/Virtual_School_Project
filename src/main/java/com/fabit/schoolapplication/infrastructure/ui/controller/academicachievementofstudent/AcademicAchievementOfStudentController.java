package com.fabit.schoolapplication.infrastructure.ui.controller.academicachievementofstudent;

import com.fabit.schoolapplication.infrastructure.ui.controller.academicachievementofstudent.dto.request.AchievementForStudentDtoRequest;
import com.fabit.schoolapplication.infrastructure.ui.controller.academicachievementofstudent.dto.request.AddJournalByDisciplineForStudentRequestDto;
import com.fabit.schoolapplication.infrastructure.ui.controller.academicachievementofstudent.dto.response.AchievementOfStudentResponseDto;
import com.fabit.schoolapplication.infrastructure.ui.controller.academicachievementofstudent.dto.response.AllAchievementsOfStudentByDisciplineResponseDto;
import com.fabit.schoolapplication.infrastructure.ui.controller.academicachievementofstudent.dto.response.AllDisciplinesOfStudentResponseDto;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/academic-achievement-of-student")
@RequiredArgsConstructor
public class AcademicAchievementOfStudentController {

  /**
   * Создать новый журнал общей академической успеваемости ученика.
   *
   * @param studentId - идентификатор ученика
   * @return ResponseEntity with status CREATED
   */
  @PostMapping("{studentId}/")
  public ResponseEntity<?> createNewJournalForStudent(@PathVariable long studentId) {
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  /**
   * Удалить журнал общей академической успеваемости ученика.
   *
   * @param studentId - идентификатор ученика
   * @return ResponseEntity with status NO_CONTENT
   */
  @DeleteMapping("{studentId}/")
  public ResponseEntity<?> deleteJournalForStudent(@PathVariable String studentId) {
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  /**
   * Добавить в журнал общей академической успеваемости ученика.
   *
   * @param requestDto - AddJournalByDisciplineForStudentRequestDto
   * @return ResponseENtity with status CREATED
   */
  @PostMapping("discipline/")
  public ResponseEntity<?> addJournalForStudentByDiscipline(
      @RequestBody AddJournalByDisciplineForStudentRequestDto requestDto) {
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  /**
   * Удалить из журнала общей академической успеваемости ученика.
   *
   * @param studentId  - идентификатор ученика
   * @param discipline - дисциплина
   * @return ResponseEntity with status NO_CONTENT
   */
  @DeleteMapping("discipline/{studentId}/{discipline}/")
  public ResponseEntity<?> deleteJournalForStudentByDiscipline(
      @PathVariable long studentId, @PathVariable String discipline) {
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  /**
   * Получить все доступные ученику дисциплины.
   *
   * @param studentId - идентификатор ученика
   * @return ResponseEntity with AllDIsciplinesOfStudentsResponseDto
   */
  @GetMapping("discipline/{studentId}/")
  public ResponseEntity<AllDisciplinesOfStudentResponseDto> getAllJournalsForStudentByDiscipline(
      @PathVariable long studentId) {
    return ResponseEntity.status(HttpStatus.OK).body(new AllDisciplinesOfStudentResponseDto());
  }

  /**
   * Получить все отметки об успеваемости ученика по данной дисциплине.
   *
   * @param studentId  - идентификатор ученика
   * @param discipline - дисциплина
   * @return ResponseEntity with AllAchievementsOfStudentByDisciplineResponseDto
   */
  @GetMapping("discipline/achievement/{studentId}/")
  public ResponseEntity<AllAchievementsOfStudentByDisciplineResponseDto> getJournalForStudentByDiscipline(
      @PathVariable long studentId, @RequestParam String discipline) {
    return ResponseEntity.status(HttpStatus.OK).body(
        new AllAchievementsOfStudentByDisciplineResponseDto()
    );
  }

  /**
   * Получить отметку об успеваемости ученика по данной дисциплине и дате урока.
   *
   * @param studentId    - идентификатор ученика
   * @param dateOfLesson - дата урока
   * @param discipline   - дисциплина урока
   * @return ResponseEntity with AchievementOfStudentResponseDto
   */
  @GetMapping("discipline/achievement/{studentId}/{dateOfLesson}/")
  public ResponseEntity<AchievementOfStudentResponseDto> getJournalForStudentByDiscipline(
      @PathVariable long studentId, @PathVariable String dateOfLesson,
      @RequestParam String discipline) {
    return ResponseEntity.status(HttpStatus.OK).body(
        new AchievementOfStudentResponseDto()
    );
  }

  /**
   * Добавить отметку об успеваемости ученику.
   *
   * @param achievement - отметка об успеваемости
   * @return Response Entity with status CREATED
   */
  @PostMapping("discipline/achievement/")
  public ResponseEntity<?> addAchievementForStudent(
      @RequestBody AchievementForStudentDtoRequest achievement) {
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  /**
   * Изменить отметку об успеваемости ученику.
   *
   * @param achievement - отметка об успеваемости
   * @return ResponseEntity with status ACCEPTED.
   */
  @PutMapping("discipline/achievement/")
  public ResponseEntity<?> changeAchievementForStudent(
      @RequestBody AchievementForStudentDtoRequest achievement) {
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  /**
   * Удалить отметку об успеваемости ученику.
   *
   * @param achievement - отметка об успеваемости
   * @return ResponseEntity with status NO_CONTENT
   */
  @DeleteMapping("discipline/achievement/")
  public ResponseEntity<?> deleteAchievementForStudent(
      @RequestBody AchievementForStudentDtoRequest achievement) {
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
