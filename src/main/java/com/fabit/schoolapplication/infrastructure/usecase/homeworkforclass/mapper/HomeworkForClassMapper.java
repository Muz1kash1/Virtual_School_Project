package com.fabit.schoolapplication.infrastructure.usecase.homeworkforclass.mapper;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClass;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.lesson.HomeworkForClassEntity;
import org.springframework.stereotype.Service;

@Service
public class HomeworkForClassMapper {

  public HomeworkForClassEntity mapLessonToEntity(HomeworkForClass homeworkForClass) {
    HomeworkForClassEntity homeworkForClassEntity = new HomeworkForClassEntity();
    homeworkForClassEntity.setDiscipline(homeworkForClass.getDiscipline());
    homeworkForClassEntity.setHomeworkTask(homeworkForClass.getTask());
    homeworkForClassEntity.setId(1l);
    homeworkForClassEntity.setDate(homeworkForClass.getDate());
    return homeworkForClassEntity;
  }

  public HomeworkForClass mapEntityToLesson(HomeworkForClassEntity homeworkForClassEntity) {
    HomeworkForClass homeworkForClass = HomeworkForClass.of(homeworkForClassEntity.getDiscipline(),homeworkForClassEntity.getDate());
    homeworkForClass.setHomeworkText(homeworkForClassEntity.getHomeworkTask());
    return homeworkForClass;
  }
}
