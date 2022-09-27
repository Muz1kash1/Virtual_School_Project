package com.fabit.schoolapplication.infrastructure.persisnence.impl;

import com.fabit.schoolapplication.application.usecase.access.teacher.TeacherService;
import com.fabit.schoolapplication.domain.teacher.Teacher;
import com.fabit.schoolapplication.infrastructure.event.TeacherDeletedEvent;
import com.fabit.schoolapplication.infrastructure.persisnence.mapper.TeacherPersistenceMapper;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.webjars.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

  private final TeacherPersistenceMapper teacherPersistenceMapper;
  private final TeacherRepository teacherRepository;
  private final ApplicationEventPublisher eventPublisher;

  @Override
  public void save(Teacher teacher) {
    TeacherEntity teacherEntity = teacherPersistenceMapper.mapDomainToEntity(teacher);
    teacherRepository.save(teacherEntity);
  }

  @Override
  public void deleteById(long teacherId) {
    teacherRepository.deleteById(teacherId);
    TeacherDeletedEvent event = new TeacherDeletedEvent(this, teacherId);
    eventPublisher.publishEvent(event);
    log.info("EVENT: " + event);
  }

  @Override
  public Teacher findById(long teacherId) {
    Optional<TeacherEntity> teacher = teacherRepository.findById(teacherId);
    if (teacher.isEmpty()) {
      throw new NotFoundException("Учителя с id " + teacherId + " нет в БД");
    } else {
      return teacherPersistenceMapper.mapEntityToDomain(teacher.get());
    }
  }

  @Override
  public List<Teacher> findAll() {
    List<Teacher> teachers = new ArrayList<>();
    teacherRepository.findAll()
      .forEach(teacherEntity -> teachers.add(teacherPersistenceMapper.mapEntityToDomain(teacherEntity)));
    return teachers;
  }
}
