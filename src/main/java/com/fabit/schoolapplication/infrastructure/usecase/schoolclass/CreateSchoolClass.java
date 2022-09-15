package com.fabit.schoolapplication.infrastructure.usecase.schoolclass;

import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.SchoolClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.StudentInClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.SchoolClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentInClassRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Lazy(value = false)
public class CreateSchoolClass {

  private final SchoolClassRepository schoolClassRepository;
  private final StudentInClassRepository studentInClassRepository;

  /**
   * Создание школьного класса.
   *
   * @param parallel - параллель (1-11)
   * @param litera   - литера (А-Я без ЪЬ)
   */
  public SchoolClassEntity execute(int parallel, String litera) {
    SchoolClass schoolClass
        = SchoolClass.of(SchoolClassId.of(0), SchoolClassName.of(parallel, litera));
    SchoolClassEntity schoolClassEntity = SchoolClassEntity.of(schoolClass);

    return schoolClassRepository.save(schoolClassEntity);
  }

  /**
   * Создание школьного класса с учениками.
   *
   * @param parallel   - параллель (1-11)
   * @param litera     - литера (А-Я без ЪЬ)
   * @param studentIds - List из идентификаторов учеников, входящих в класс
   */
  @Transactional
  public void execute(int parallel, String litera, List<Long> studentIds) {

    List<StudentId> students = new ArrayList<>();
    for (Long id : studentIds) {
      students.add(StudentId.of(id));
    }

    SchoolClass schoolClass = SchoolClass.of(
        SchoolClassId.of(1), SchoolClassName.of(parallel, litera), students);

    SchoolClassEntity schoolClassEntity
        = schoolClassRepository.save(SchoolClassEntity.of(schoolClass));

    for (Long studentId : studentIds) {
      studentInClassRepository.save(
          StudentInClassEntity.of(schoolClassEntity.getId(), studentId));
      log.info("StudentInClassEntity вставлен в БД: " + studentId);
    }
  }

  @Transactional
  public void execute(SchoolClass schoolClass) {

    SchoolClassEntity schoolClassEntity
        = schoolClassRepository.save(SchoolClassEntity.of(schoolClass));

    for (StudentId studentId : schoolClass.getStudents()) {
      studentInClassRepository.save(
          StudentInClassEntity.of(schoolClassEntity.getId(), studentId.getValue()));
      log.info("StudentInClassEntity вставлен в БД: " + studentId);
    }
  }

}
