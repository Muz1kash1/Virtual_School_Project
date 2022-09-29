package com.fabit.schoolapplication.application.usecase.access.homeworkforclass;

import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClass;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;

public interface HomeworkForClassService {

  void save(HomeworkForClass homeworkForClass);

  void deleteById(HomeworkForClassId id);

  HomeworkForClass findById(HomeworkForClassId id);

  void deleteAll();

  void changeHomework(HomeworkForClassId id, String task);
}
