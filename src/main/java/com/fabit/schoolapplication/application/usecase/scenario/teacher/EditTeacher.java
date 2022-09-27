package com.fabit.schoolapplication.application.usecase.scenario.teacher;

import com.fabit.schoolapplication.application.mapper.TeacherServiceMapper;
import com.fabit.schoolapplication.domain.teacher.Teacher;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import com.fabit.schoolapplication.infrastructure.ui.controller.teacher.dto.DeactivateDto;
import java.time.LocalDate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;


@Service
@Slf4j
@RequiredArgsConstructor
public class EditTeacher {

  private final TeacherRepository teacherRepository;
  private final TeacherServiceMapper teacherServiceMapper;


  /**
   * Активировать учителя (вышел на работу).
   *
   * @param teacherId - идентификатор учителя
   * @return TeacherEntity
   */
  @Transactional
  public TeacherEntity activate(long teacherId) {

    Optional<TeacherEntity> teacherOptional = teacherRepository.findById(teacherId);

    if (teacherOptional.isEmpty()) {
      throw new NotFoundException("Учителя с id " + teacherId + " нет в БД");
    }

    Teacher teacher = teacherServiceMapper.mapEntityToDomain(teacherOptional.get());
    teacher.activate();
    TeacherEntity result = teacherServiceMapper.mapDomainToEntity(teacher);
    result.setId(teacherOptional.get().getId());
    teacherRepository.save(result);
    log.info("Учитель активирован и вновь может работать: " + result);
    return result;
  }

  /**
   * Деактивировать учителя (более не может работать).
   *
   * @param deactivateDto - ДТО деактивации учителя
   * @return TeacherEntity
   */
  @Transactional
  public TeacherEntity deactivate(DeactivateDto deactivateDto) {

    Optional<TeacherEntity> teacherOptional
        = teacherRepository.findById(deactivateDto.getTeacherId());

    if (teacherOptional.isEmpty()) {
      throw new NotFoundException("Учителя с id " + deactivateDto.getTeacherId() + " нет в БД");
    }

    Teacher teacher = teacherServiceMapper.mapEntityToDomain(teacherOptional.get());

    teacher.deactivate(
        LocalDate.parse(deactivateDto.getFrom()), LocalDate.parse(deactivateDto.getTo()));

    TeacherEntity result = teacherServiceMapper.mapDomainToEntity(teacher);

    result.setId(teacherOptional.get().getId());

    teacherRepository.save(result);
    log.info("Учитель деактивирован и не может работать: " + result);
    return result;
  }
}
