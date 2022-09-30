package com.fabit.schoolapplication.infrastructure.persisnence.repository;

import com.fabit.schoolapplication.infrastructure.persisnence.entity.journalofstudent.JournalByDisciplineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface JournalByDisciplineRepository extends JpaRepository<JournalByDisciplineEntity, Long> {
  @Query(value = "SELECT nextval('journal_by_discipline_id_seq') from journal_by_discipline_id_seq", nativeQuery = true)
  Long getNextId();

  @Transactional
  @Modifying
  @Query("delete from JournalByDisciplineEntity j where j.journalOfStudentEntity.student.id = ?1 and j.discipline = ?2")
  void deleteByJournalOfStudentEntityStudentId(Long studentId, String discipline);
}
