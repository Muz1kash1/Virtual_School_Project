package com.fabit.schoolapplication.infrastructure.usecase.schoolclass;

import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.SchoolClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.SchoolClassRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Lazy(value = false)
public class GetSchoolClass {

  private final SchoolClassRepository schoolClassRepository;

  public List<SchoolClassEntity> all() {
    List<SchoolClassEntity> schoolClasses = schoolClassRepository.findAll();
    if (!schoolClasses.isEmpty()) {
      return schoolClasses;
    } else {
      throw new NoSuchElementException("В БД нет школьных классов.");
    }
  }

  public SchoolClassEntity byId(long id) {
    Optional<SchoolClassEntity> schoolClass = schoolClassRepository.findById(id);
    if (schoolClass.isPresent()) {
      return schoolClass.get();
    } else {
      throw new NoSuchElementException("Класса с id " + id + " не существует.");
    }
  }

  public SchoolClassEntity byName(int parallel, String litera) {
    return schoolClassRepository.findByParallelAndLitera(parallel, litera);
  }

}
