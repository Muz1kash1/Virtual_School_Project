package com.fabit.schoolapplication.application.usecase.virtual_school.teacher;

import com.fabit.schoolapplication.application.mapper.TeacherServiceMapper;
import com.fabit.schoolapplication.domain.teacher.Teacher;
import com.fabit.schoolapplication.infrastructure.controller.virtual_school.teacher.dto.TeacherDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateTeacher {
  private final TeacherRepository teacherRepository;
  private final TeacherServiceMapper teacherServiceMapper;

  /**
   * Создать учителя из пришедшего DTO.
   *
   * @param teacherDto - DTO учителя
   * @return TeacherEntity
   */
  @Transactional
  public TeacherEntity execute(TeacherDto teacherDto) {
    Teacher teacher = teacherServiceMapper.mapDtoToDomain(teacherDto);
    TeacherEntity teacherEntity = teacherServiceMapper.mapDomainToEntity(teacher);

    teacherRepository.save(teacherEntity);
    return teacherEntity;
  }
}
