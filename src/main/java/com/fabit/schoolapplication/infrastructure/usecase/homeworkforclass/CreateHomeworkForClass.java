package com.fabit.schoolapplication.infrastructure.usecase.homeworkforclass;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClass;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import com.fabit.schoolapplication.infrastructure.usecase.homeworkforclass.mapper.HomeworkForClassMapper;
import java.time.LocalDate;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateHomeworkForClass {
  private final HomeworkForClassRepository homeworkForClassRepository;
  private final HomeworkForClassMapper homeworkForClassMapper;

  /**
   * этот метод создает урок
   * @param discipline дисциплина урока
   */
  @Transactional
  public void execute(Discipline discipline, LocalDate date){
    HomeworkForClass homeworkForClass = HomeworkForClass.of(discipline,date);
    homeworkForClassRepository.save(homeworkForClassMapper.mapLessonToEntity(homeworkForClass));
  }
}
