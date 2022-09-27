package com.fabit.schoolapplication.infrastructure.persisnence.repository;

import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.SchoolClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClassEntity, Long> {
  SchoolClassEntity findByParallelAndLitera(int parallel, String litera);

  @Query(value = "SELECT last_value + 1 from school_class_id_seq", nativeQuery = true)
  Long getNextId();
}
