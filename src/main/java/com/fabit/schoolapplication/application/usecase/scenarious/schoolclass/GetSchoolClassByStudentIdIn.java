package com.fabit.schoolapplication.application.usecase.scenarious.schoolclass;

import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.SchoolClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.StudentInClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.SchoolClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentInClassRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetSchoolClassByStudentIdIn {

  private final StudentInClassRepository studentInClassRepository;
  private final SchoolClassRepository schoolClassRepository;

  /**
   * Метод, определяющий и выдающий школьный класс по идентификатору ученика.
   *
   * @param studentId - идентификатор ученика
   * @return SchoolClassEntity
   */
  public SchoolClassEntity execute(StudentId studentId) {
    StudentInClassEntity studentInClassEntity
        = studentInClassRepository.findByStudentId(studentId.getValue());
    Optional<SchoolClassEntity> schoolClassEntity
        = schoolClassRepository.findById(studentInClassEntity.getSchoolClassId());

    if (schoolClassEntity.isPresent()) {
      return schoolClassEntity.get();
    } else {
      throw new NoSuchElementException(
          "Ученик с id " + studentId.getValue() + " не числится в классе.");
    }
  }

}
