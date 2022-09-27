package com.fabit.schoolapplication.application.usecase.scenario.schoolclass;

import com.fabit.schoolapplication.application.usecase.access.schoolclass.SchoolClassService;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.student.StudentId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

@RequiredArgsConstructor
public class GetSchoolClassUseCase {

  final SchoolClassService schoolClassService;

  /**
   * Получить список всех школьных классов.
   *
   * @return List of SchoolClass
   */
  public List<SchoolClass> all() {
    return schoolClassService.getAll();
  }

  /**
   * Получить школьный класс по идентификатору.
   *
   * @param id - идентификатор школьного класса
   * @return SchoolClass
   */
  public SchoolClass byId(long id) throws Exception {
    return schoolClassService.getById(id);
  }

  /**
   * Получить школьный класс по названию.
   *
   * @param parallel - параллель (1-11)
   * @param litera   - литера школьного класса (А-Я без ЪЬ)
   * @return SchoolClass
   */
  public SchoolClass byName(int parallel, String litera) {
    return schoolClassService.getByName(parallel, litera);
  }

  /**
   * Получить школьный класс по идентификатору ученика.
   *
   * @param id - идентификатор ученика
   * @return SchoolClass
   */
  public SchoolClass getByStudentId(long id) {
    try {
      return schoolClassService.getByStudentId(StudentId.of(id));
    } catch (NotFoundException e) {
      throw new RuntimeException(e);
    }
  }

}
