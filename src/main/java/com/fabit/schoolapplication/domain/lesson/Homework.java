package com.fabit.schoolapplication.domain.lesson;

import lombok.Getter;

import java.io.File;
import java.util.HashMap;
@Getter
public class Homework {
    private final HashMap<Long,File> responseFromStudents;
    private String homeworkText;

    public Homework() {
        responseFromStudents = new HashMap<>();
    }


    /**
     * Добавить выполненную домашнюю работу
     * @param studentId айди студента
     * @param file файл с выполненной домашкой
     */
    public void setResponseFromStudent(Long studentId,File file) {
        responseFromStudents.put(studentId,file);
    }

    public void setHomeworkText(String homeworkText){
        this.homeworkText = homeworkText;
    }
}
