package com.fabit.schoolapplication.infrastructure.usecase.teacher;

import com.fabit.schoolapplication.domain.teacher.Teacher;
import com.fabit.schoolapplication.infrastructure.controller.teacher.dto.DeactivateDto;
import com.fabit.schoolapplication.infrastructure.controller.teacher.dto.StandingYearsDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import com.fabit.schoolapplication.infrastructure.usecase.teacher.mapper.TeacherServiceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class EditTeacher {
  private final TeacherRepository teacherRepository;
  private final TeacherServiceMapper teacherServiceMapper;

  @Transactional
  public TeacherEntity changeStandingYears(StandingYearsDto standingYearsDto) {
    TeacherEntity teacherEntity = teacherRepository.findById(standingYearsDto.getTeacherId()).get();
    teacherEntity.setStandingYears(standingYearsDto.getStandingYears());
    teacherRepository.save(teacherEntity);
    return teacherEntity;
  }

  @Transactional
  public TeacherEntity activate(long teacherId) {
    TeacherEntity teacherEntity = teacherRepository.findById(teacherId).get();
    Teacher teacher = teacherServiceMapper.mapEntityToDomain(teacherEntity);
    teacher.activate();
    TeacherEntity result = teacherServiceMapper.mapDomainToEntity(teacher);
    result.setId(teacherEntity.getId());
    teacherRepository.save(result);
    return result;
  }

  @Transactional
  public TeacherEntity deactivate(DeactivateDto deactivateDto) {
    TeacherEntity teacherEntity = teacherRepository.findById(deactivateDto.getTeacherId()).get();
    Teacher teacher = teacherServiceMapper.mapEntityToDomain(teacherEntity);
    teacher.deactivate(
        LocalDate.parse(deactivateDto.getFrom()), LocalDate.parse(deactivateDto.getTo()));
    TeacherEntity result = teacherServiceMapper.mapDomainToEntity(teacher);
    result.setId(teacherEntity.getId());
    teacherRepository.save(result);
    return result;
  }
}
