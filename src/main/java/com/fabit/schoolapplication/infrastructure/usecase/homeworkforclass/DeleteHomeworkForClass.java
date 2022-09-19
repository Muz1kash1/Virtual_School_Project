package com.fabit.schoolapplication.infrastructure.usecase.homeworkforclass;

import com.fabit.schoolapplication.domain.homeworkforclass.LessonId;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class DeleteHomeworkForClass {
  private final HomeworkForClassRepository homeworkForClassRepository;

  /**
   * метод удаления урока
   * @param lessonId айди урока
   */
  public void execute(LessonId lessonId){
    homeworkForClassRepository.deleteById(lessonId.getValue());
  }
}
