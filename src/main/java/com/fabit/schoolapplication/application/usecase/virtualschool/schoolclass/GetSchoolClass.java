package com.fabit.schoolapplication.application.usecase.virtualschool.schoolclass;

import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.SchoolClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.SchoolClassRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetSchoolClass {

  private final SchoolClassRepository schoolClassRepository;

  /**
   * Получить список всех школьных классов.
   *
   * @return List of SchoolClassEntity
   */
  public List<SchoolClassEntity> all() {
    List<SchoolClassEntity> schoolClasses = schoolClassRepository.findAll();
    if (!schoolClasses.isEmpty()) {
      return schoolClasses;
    } else {
      throw new NoSuchElementException("В БД нет школьных классов.");
    }
  }

  /**
   * Получить школьный класс по идентификатору.
   *
   * @param id - идентификатор школьного класса
   * @return SchoolClassEntity
   */
  public SchoolClassEntity byId(long id) {
    Optional<SchoolClassEntity> schoolClass = schoolClassRepository.findById(id);
    if (schoolClass.isPresent()) {
      return schoolClass.get();
    } else {
      throw new NoSuchElementException("Класса с id " + id + " не существует.");
    }
  }

  /**
   * Получить школьный класс по названию.
   *
   * @param parallel - параллель (1-11)
   * @param litera   - литера школьного класса (А-Я без ЪЬ)
   * @return SchoolClassEntity
   */
  public SchoolClassEntity byName(int parallel, String litera) {
    return schoolClassRepository.findByParallelAndLitera(parallel, litera);
  }

}
