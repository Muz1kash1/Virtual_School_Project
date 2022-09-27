package com.fabit.schoolapplication.application.usecase.scenario.teacher;

import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetTeacher {
  private final TeacherRepository teacherRepository;

  /**
   * Получить учителя по идентификатору.
   *
   * @param id - идентификатор учителя
   * @return TeacherEntity
   */
  public TeacherEntity byId(long id) {
    Optional<TeacherEntity> teacherEntity = teacherRepository.findById(id);

    if (teacherEntity.isPresent()) {
      return teacherEntity.get();
    } else {
      throw new NotFoundException("Учителя с id " + id + " не существует.");
    }
  }

  public List<TeacherEntity> all() {
    return teacherRepository.findAll();
  }
}
