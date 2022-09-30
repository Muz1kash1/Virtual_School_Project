package com.fabit.schoolapplication.infrastructure.persisnence.impl;

import com.fabit.schoolapplication.application.usecase.access.journalofstudent.JournalOfStudentService;
import com.fabit.schoolapplication.application.usecase.scenario.journalofstudent.dto.AllDisciplinesOfStudentDto;
import com.fabit.schoolapplication.domain.journalofstudent.agregate.JournalOfStudent;
import com.fabit.schoolapplication.domain.journalofstudent.id.JournalOfStudentId;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.journalofstudent.JournalByDisciplineEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.journalofstudent.JournalOfStudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.mapper.JournalPersistenceMapper;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.JournalOfStudentRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JournalOfStudentServiceImpl implements JournalOfStudentService {
  private final JournalOfStudentRepository journalOfStudentRepository;
  private final JournalPersistenceMapper journalPersistenceMapper;
  private final StudentRepository studentRepository;

  @Override
  @Transactional
  public void save(long studentId) {
    if (studentRepository.existsById(studentId)) {
      JournalOfStudent journalOfStudent = JournalOfStudent.of(
        JournalOfStudentId.of(
          journalOfStudentRepository.getNextId()
        ),
        StudentId.of(
          studentId
        )
      );
      JournalOfStudentEntity journalOfStudentEntity
        = journalPersistenceMapper.mapDomainJournalOfStudentToEntity(
        journalOfStudent
      );
      journalOfStudentRepository.save(journalOfStudentEntity);
    } else {
      throw new IllegalArgumentException("Студента с id: " + studentId + " не существует");
    }
  }

  @Override
  public void delete(long studentId) {
    journalOfStudentRepository.deleteByStudentId(studentId);
  }

  @Override
  public AllDisciplinesOfStudentDto getAllByStudent(long studentId) {
    List<JournalByDisciplineEntity> journalOfStudentEntities
      = journalOfStudentRepository.findJournalOfStudentEntitiesByStudentId(
      studentId
    );
    List<String> disciplines = new ArrayList<>();

    journalOfStudentEntities
      .forEach(journalOfStudentEntity -> disciplines.add(
        journalOfStudentEntity.getDiscipline().getTextView()
      ));
    return new AllDisciplinesOfStudentDto(disciplines);
  }
}
