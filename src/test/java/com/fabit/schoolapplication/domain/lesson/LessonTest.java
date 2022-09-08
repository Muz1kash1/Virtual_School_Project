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

        lesson.setHomeworkText("Тест");
        lesson.uploadCompletedHomework(1l,file);
    }

    @Test
    @DisplayName("Присвоение домашке текста задания работает корректно")
    void setHomeworkTextTest(){
        Assertions.assertEquals("Тест",lesson.getHomeworkForLesson().getHomeworkText());
    }

    @Test
    @DisplayName("Загрузка домашнего задания работает корректно")
    void uploadHomeworkTest(){
        Assertions.assertEquals(file,lesson.getHomeworkForLesson().getResponseFromStudents().get(1l));
    }


}
