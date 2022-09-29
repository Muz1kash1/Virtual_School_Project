package com.fabit.schoolapplication.application.usecase.access.loadedhomework;

import com.fabit.schoolapplication.domain.loadedhomework.LoadedHomework;
import com.fabit.schoolapplication.domain.loadedhomework.LoadedHomeworkId;

public interface LoadedHomeworkService {

  void save(LoadedHomework loadedHomework);

  LoadedHomework getReferenceById(LoadedHomeworkId id);

  void deleteAll();

}
