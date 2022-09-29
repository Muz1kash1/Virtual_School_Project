package com.fabit.schoolapplication.infrastructure.ui.controller.homeworkforclass;

import com.fabit.schoolapplication.application.usecase.scenario.homeworkforclass.GetHomeworkForClassUseCase;
import com.fabit.schoolapplication.domain.generalvalueobject.discipline.Discipline;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClass;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeworkForClassControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  GetHomeworkForClassUseCase getHomeworkForClassUseCase;

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
    HomeworkForClass homeworkForClass = HomeworkForClass.of(Discipline.ENGLISH, LocalDate.of(1, 1, 1),
      SchoolClassId.of(5L), HomeworkForClassId.of(2L)
    );
    homeworkForClass.setHomeworkText("abc");
    when(getHomeworkForClassUseCase.execute(HomeworkForClassId.of(2L)))
      .thenReturn(homeworkForClass);

    mockMvc.perform(get("/homework-for-class/2"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id", is(2)))
      .andExpect(jsonPath("$.discipline", is("ENGLISH")))
      .andExpect(jsonPath("$.task", is("abc")))
      .andExpect(jsonPath("$.schoolClassId", is(5)));
  }

}
