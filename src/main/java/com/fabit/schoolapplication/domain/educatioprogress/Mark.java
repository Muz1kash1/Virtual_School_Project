package com.fabit.schoolapplication.domain.educatioprogress;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Сущность Оценка хранит в себе ссылку на отметку об успеваемости которой эта оценка принадлежит
 * Если нужно создать двойную оценку то в момент создания объекта EducationProgress создаются два
 * объекта Mark с полем EducationProgressId равному id объекта EducationProgress
 *
 * @author SmirnovMA
 */
@Getter
@EqualsAndHashCode
public final class Mark {
  private MarkId markId;

  private EducationProgressId educationProgressId;
  private int value;

  private Mark() {}

  /**
   * Статическая фабрика по созданию объектов Mark
   *
   * @param markId id оценки
   * @param educationProgressId id отметки об успеваемости которой принадлежит эта оценка
   * @param value значение оценки
   * @return объект Mark
   */
  public static Mark of(MarkId markId, EducationProgressId educationProgressId, int value) {
    Mark mark = new Mark();
    mark.markId = markId;
    mark.educationProgressId = educationProgressId;
    mark.setValue(value);
    return mark;
  }

  /**
   * Оценка представленная в виде значения от 2 до 5 баллов
   *
   * @param value значение оценки
   */
  private void setValue(int value) {
    if (value >= 2 && value <= 5) {
      this.value = value;
    } else {
      throw new IllegalArgumentException("Неверный формат оценки");
    }
  }
}
