package com.fabit.schoolapplication.infrastructure.controller.homeworkforclass;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fabit.schoolapplication.application.usecase.virtualschool.homeworkforclass.GetHomeworkForClass;
import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.homeworkforclass.dto.HomeworkForClassDto;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeworkForClassControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  GetHomeworkForClass getHomeworkForClass;

  @Test
  @DisplayName("Установка ДЗ для класса должно возвращать успешное назначение")
  void addHomeworkForClassTest() throws Exception {

    final String jsonOfHomework = """
        {
        "id": 1,
        "discipline": "BIOLOGY",
        "task": "task",
        "schoolClassId": 1,
        "date": "2022-09-21"
        }
        """;

    mockMvc.perform(post("/homework-for-class")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonOfHomework))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$", is("Домашнее задание задано")));
  }

  @Test
  @DisplayName("Получение ДЗ класса должно возвращать соответствующее ДЗ")
  void getHomeworkForClassTest() throws Exception {
    HomeworkForClassDto homeworkForClassDto = new HomeworkForClassDto(
        2L,
        Discipline.ENGLISH,
        "abc", 5L,
        LocalDate.now()
    );

    when(getHomeworkForClass.execute(2))
        .thenReturn(homeworkForClassDto);

    mockMvc.perform(get("/homework-for-class/2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(2)))
        .andExpect(jsonPath("$.discipline", is("ENGLISH")))
        .andExpect(jsonPath("$.task", is("abc")))
        .andExpect(jsonPath("$.schoolClassId", is(5)));
  }

}
