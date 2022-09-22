package com.fabit.schoolapplication.infrastructure.persisnence.repository;

import com.fabit.schoolapplication.infrastructure.persisnence.entity.homeworkforclass.HomeworkForClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkForClassRepository extends JpaRepository<HomeworkForClassEntity, Long> {

  /**
   * Метод возвращающий следующий ид в таблице назначенного дз.
   *
   * @return численное значение следующего ид
   */
  @Query(value = "SELECT nextval('lesson_id_seq') from lesson_id_seq", nativeQuery = true)
  Long getNextId();
}
