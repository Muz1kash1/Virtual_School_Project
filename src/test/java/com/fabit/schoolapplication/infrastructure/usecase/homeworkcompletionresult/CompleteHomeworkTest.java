package com.fabit.schoolapplication.infrastructure.usecase.homeworkcompletionresult;

import static org.mockito.Mockito.when;

import com.fabit.schoolapplication.domain.FullName;
import com.fabit.schoolapplication.domain.Passport;
import com.fabit.schoolapplication.domain.Snils;
import com.fabit.schoolapplication.domain.teacher.Teacher;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import com.fabit.schoolapplication.infrastructure.controller.homeworkcompletionresult.dto.HomeworkCompletionResultDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkCompletionResultRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.LessonRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import com.fabit.schoolapplication.infrastructure.usecase.homeworkcompletionresult.mapper.HomeworkMapperService;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

@SpringBootTest
public class CompleteHomeworkTest {
  @Autowired
  HomeworkMapperService homeworkMapperService;
  @Autowired
  CompleteHomework completeHomework;
  @MockBean
  HomeworkCompletionResultRepository repository;
  @Test
  void uploadCompletedHomework(){
    HomeworkCompletionResultDto dto = new HomeworkCompletionResultDto(1l,1l,"test",1l);
    completeHomework.uploadCompletedHomework(dto);
    Assertions.assertNotNull(repository.findAll());
    Assertions.assertNotNull(repository.getReferenceById(1l));
//    Assertions.assertEquals(homeworkMapperService.mapToHomeworkCompletionResultEntity(homeworkMapperService.mapToHomeworkCompletionResult(dto)).getTaskCompletionResult(),repository.getReferenceById(1l).getTaskCompletionResult());
  }

}
