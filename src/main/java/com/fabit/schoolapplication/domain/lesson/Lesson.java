package com.fabit.schoolapplication.domain.lesson;

import lombok.Getter;

import java.io.File;
import java.util.HashMap;
@Getter
public class Lesson {
    /**
     * Мапа формата айди студента - домашняя работа
     */
    private final Homework homeworkForLesson;


    public Lesson() {
        this.homeworkForLesson = new Homework();
    }

    /**
     * Метод задания текста домашнего задания для всех прикрепленных к этому уровку студентов
     *
     * @param homeworkText - текст домашнего задания
     */
    public void setHomeworkText(String homeworkText) {
        homeworkForLesson.setHomeworkText(homeworkText);
    }

    /**
     * Метод загрузки выполненной домашки файлом
     *
     * @param studentId - Айдишник студента который загружает выполненную домашнюю работу
     * @param file      - файл с выполненной домашней работой
     */
    public void uploadCompletedHomework(Long studentId, File file) {
        homeworkForLesson.setResponseFromStudent(studentId,file);
    }
}