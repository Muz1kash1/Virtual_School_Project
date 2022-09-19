package com.fabit.schoolapplication.infrastructure.persisnence.repository;

import com.fabit.schoolapplication.infrastructure.persisnence.entity.lesson.HomeworkForClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkForClassRepository extends JpaRepository<HomeworkForClassEntity,Long>{

}
