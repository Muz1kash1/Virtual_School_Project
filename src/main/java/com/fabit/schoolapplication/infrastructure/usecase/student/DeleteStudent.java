package com.fabit.schoolapplication.infrastructure.usecase.student;

import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteStudent {
  final StudentRepository studentRepository;

  public void deleteStudent(long id) {
    studentRepository.deleteById(id);
  }
}
