package com.fabit.schoolapplication.persisnence.repository;

import com.fabit.schoolapplication.persisnence.entity.lesson.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<LessonEntity,Long>{

}
