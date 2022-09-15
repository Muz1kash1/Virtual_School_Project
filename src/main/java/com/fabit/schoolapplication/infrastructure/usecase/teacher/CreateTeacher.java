package com.fabit.schoolapplication.infrastructure.usecase.teacher;

import com.fabit.schoolapplication.domain.teacher.Teacher;
import com.fabit.schoolapplication.infrastructure.controller.teacher.dto.TeacherDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import com.fabit.schoolapplication.infrastructure.usecase.teacher.mapper.TeacherServiceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateTeacher {
  private final TeacherRepository teacherRepository;
  private final TeacherServiceMapper teacherServiceMapper;

  @Transactional
  public TeacherEntity execute(TeacherDto teacherDto) {
    Teacher teacher = teacherServiceMapper.mapDtoToDomain(teacherDto);
    TeacherEntity teacherEntity = teacherServiceMapper.mapDomainToEntity(teacher);
    teacherRepository.save(teacherEntity);
    return teacherEntity;
  }
}
