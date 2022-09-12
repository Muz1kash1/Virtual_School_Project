package com.fabit.schoolapplication.persisnence.repository;

import com.fabit.schoolapplication.infrastructure.persisnence.entity.homeworkcompletionresult.HomeworkCompletionResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkCompletionResultRepository extends JpaRepository<HomeworkCompletionResultEntity,Long> {

}
