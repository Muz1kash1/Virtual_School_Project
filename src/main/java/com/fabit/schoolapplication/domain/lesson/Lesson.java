package com.fabit.schoolapplication.domain.lesson;

import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Getter
public class Lesson {
    /**
     * Мапа формата айди студента - домашняя работа
     */
    private final List<Homework> homeworkList;
    String homeworkTask;


    public Lesson() {
        this.homeworkList = new ArrayList<>();
    }

    /**
     * Метод задания текста домашнего задания для всех прикрепленных к этому уровку студентов
     *
     * @param homeworkTask - текст домашнего задания
     */
    public void setHomeworkText(String homeworkTask) {
        this.homeworkTask = homeworkTask;
        for (Homework homework: homeworkList){
            homework.setHomeworkTask(homeworkTask);
        }
    }

    /**
     * Метод загрузки выполненной домашки файлом
     *
     * @param studentId - Айдишник студента который загружает выполненную домашнюю работу
     * @param file      - файл с выполненной домашней работой
     */
    public void uploadCompletedHomework(Long studentId, File file) {
        for (Homework homework : homeworkList){
            if (Objects.equals(homework.getStudentId(), studentId)){
                homework.setCompletedHomework(file);
            }
        }
    }
}