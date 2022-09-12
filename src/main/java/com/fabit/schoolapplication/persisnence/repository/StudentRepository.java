package com.fabit.schoolapplication.persisnence.repository;

import com.fabit.schoolapplication.persisnence.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
