package com.fabit.schoolapplication.domain.loadedhomework.event;

import com.fabit.schoolapplication.domain.loadedhomework.LoadedHomework;

public class LoadedHomeworkUpdatedEvent implements LoadedHomeworkEvent {

  private final LoadedHomework loadedHomework;

  public LoadedHomeworkUpdatedEvent(LoadedHomework loadedHomework) {
    this.loadedHomework = loadedHomework;
  }

  @Override
  public Object getContent() {
    return this.loadedHomework;
  }
}
