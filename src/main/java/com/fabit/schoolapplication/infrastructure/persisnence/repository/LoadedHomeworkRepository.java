package com.fabit.schoolapplication.infrastructure.persisnence.repository;

import com.fabit.schoolapplication.infrastructure.persisnence.entity.loadedhomework.LoadedHomeworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadedHomeworkRepository extends JpaRepository<LoadedHomeworkEntity, Long> {

  /**
   * Метод возвращающий следующий ид в таблице загруженного дз.
   *
   * @return численное значение следующего ид
   */
  @Query(value
          = "SELECT nextval('homework_completion_result_id_seq') "
          + "from homework_completion_result_id_seq",
      nativeQuery = true)
  Long getNextId();
}
