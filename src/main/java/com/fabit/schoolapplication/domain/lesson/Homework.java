package com.fabit.schoolapplication.domain.lesson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.image.SampleModel;
import java.io.File;
import java.util.HashMap;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Homework {
    private Long studentId;
    private File completedHomework;
    private String homeworkTask;

}
