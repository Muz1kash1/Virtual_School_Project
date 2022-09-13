package com.fabit.schoolapplication.infrastructure.persisnence.repository;

import com.fabit.schoolapplication.infrastructure.persisnence.entity.homeworkcompletionresult.HomeworkCompletionResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkCompletionResultRepository extends JpaRepository<HomeworkCompletionResultEntity,Long> {

}
