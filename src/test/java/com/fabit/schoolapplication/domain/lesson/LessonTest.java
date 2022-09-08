package com.fabit.schoolapplication.domain.lesson;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

public class LessonTest {

    static Lesson lesson = new Lesson();
    static File file = new File("text.txt");

    @BeforeAll
    static void initializeAll(){
        lesson.getHomeworkList().add(new Homework(1l,null,null));
        lesson.setHomeworkText("Тест");
        lesson.uploadCompletedHomework(1l,file);
    }

    @Test
    @DisplayName("Загрузка домашнего задания работает корректно")
    void uploadHomeworkTest(){
        Assertions.assertEquals(file,lesson.getHomeworkList().get(0).getCompletedHomework());
    }

    @Test
    @DisplayName("Присвоение домашке текста задания работает корректно")
    void setHomeworkTextTest(){
        Assertions.assertEquals("Тест",lesson.getHomeworkTask());
        Assertions.assertEquals("Тест",lesson.getHomeworkList().get(0).getHomeworkTask());
    }


}
