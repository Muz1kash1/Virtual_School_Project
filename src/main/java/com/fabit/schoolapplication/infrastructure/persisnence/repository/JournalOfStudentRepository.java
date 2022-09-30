package com.fabit.schoolapplication.infrastructure.persisnence.repository;

import com.fabit.schoolapplication.infrastructure.persisnence.entity.journalofstudent.JournalByDisciplineEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.journalofstudent.JournalOfStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JournalOfStudentRepository
  extends JpaRepository<JournalOfStudentEntity, Long> {
  @Query(value = "SELECT nextval('journal_of_student_id_seq') from journal_of_student_id_seq", nativeQuery = true)
  Long getNextId();

  void deleteByStudentId(Long studentId);

  @Query(value = "SELECT * FROM journal_of_student WHERE student_id = :studentId", nativeQuery = true)
  JournalOfStudentEntity findByStudentId(@Param("studentId") Long studentId);

  @Query(value =
    "SELECT * FROM journal_by_discipline "
    + "INNER JOIN journal_of_student "
    + "ON journal_of_student.id = journal_by_discipline.journal_of_student "
    + "WHERE journal_of_student.student_id = :studentId", nativeQuery = true)
  List<JournalByDisciplineEntity> findJournalOfStudentEntitiesByStudentId(@Param("studentId") Long studentId);
}
