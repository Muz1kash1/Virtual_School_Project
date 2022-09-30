package com.fabit.schoolapplication.infrastructure.persisnence.repository;

import com.fabit.schoolapplication.infrastructure.persisnence.entity.journalofstudent.AchievementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<AchievementEntity, Long> {
  @Query(value = "SELECT nextval('achievement_id_seq') from achievement_id_seq", nativeQuery = true)
  Long getNextId();

  @Query(
    "select a from AchievementEntity a where "
      + "a.journalByDisciplineEntity.journalOfStudentEntity.student.id"
      + " = ?1 and a.journalByDisciplineEntity.discipline = ?2"
  )
  List<AchievementEntity> findAllByStudentIdAndDiscipline(Long studentId, String discipline);

  @Query(
    "select a from AchievementEntity a where "
      + "a.journalByDisciplineEntity.journalOfStudentEntity.student.id"
      + " = ?1 and a.journalByDisciplineEntity.discipline = ?2 "
      + "and a.dateOfLesson = ?3"
  )
  AchievementEntity findByDisciplineAndDateOfLessonAndStudentId(
    Long studentId, String discipline, LocalDate dateOfLesson);

  @Transactional
  @Modifying
  @Query(
    "delete from AchievementEntity a where "
      + "a.journalByDisciplineEntity.journalOfStudentEntity.student.id"
      + " = ?1 and a.journalByDisciplineEntity.discipline = ?2 "
      + "and a.dateOfLesson = ?3 and a.value = ?4"
  )
  void deleteByDisciplineAndDateOfLessonAndStudentIdAndValue(
    Long studentId, String discipline, LocalDate dateOfLesson, String value);
}
