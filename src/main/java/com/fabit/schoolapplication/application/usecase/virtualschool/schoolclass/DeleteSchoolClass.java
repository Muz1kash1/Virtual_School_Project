package com.fabit.schoolapplication.application.usecase.virtualschool.schoolclass;

import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.SchoolClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentInClassRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteSchoolClass {

  private final SchoolClassRepository schoolClassRepository;
  private final StudentInClassRepository studentInClassRepository;

  /**
   * Юзкейс удаления школьного класса.
   *
   * @param parallel - параллель (1-11)
   * @param litera   - литера (А-Я без ЪЬ)
   */
  @Transactional
  public void execute(int parallel, String litera) {
    Long classId = schoolClassRepository.findByParallelAndLitera(parallel, litera).getId();
    studentInClassRepository.deleteAllBySchoolClassId(classId);
    schoolClassRepository.deleteById(classId);
  }

  /**
   * Юзкейс удаления школьного класса по агрегату.
   *
   * @param schoolClass - агрегат школьного класса
   */
  @Transactional
  public void execute(SchoolClass schoolClass) {
    int parallel = schoolClass.getSchoolClassName().getParallel();
    String litera = schoolClass.getSchoolClassName().getLitera();

    Long classId = schoolClassRepository.findByParallelAndLitera(parallel, litera).getId();
    studentInClassRepository.deleteAllBySchoolClassId(classId);
    schoolClassRepository.deleteById(classId);
  }

}
