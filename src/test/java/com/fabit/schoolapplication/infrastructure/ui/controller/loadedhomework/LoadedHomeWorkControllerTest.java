package com.fabit.schoolapplication.infrastructure.ui.controller.loadedhomework;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fabit.schoolapplication.application.usecase.scenario.loadedhomework.CompleteHomework;
import com.fabit.schoolapplication.application.usecase.scenario.loadedhomework.GetLoadedHomework;
import com.fabit.schoolapplication.infrastructure.ui.controller.loadedhomework.dto.LoadedHomeworkDto;
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
public class LoadedHomeWorkControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  CompleteHomework completeHomework;

  @MockBean
  GetLoadedHomework getLoadedHomework;

  @Test
  @DisplayName("Загрузка ДЗ учеником должно возвращать сообщение о загрузке")
  void LoadCompletedHomeworkTest() throws Exception {

    final String jsonOfCompletedHomework = """
        {
        "homeworkId": 1,
        "studentId": 5,
        "taskCompletionResult": "complete",
        "homeworkForClassId": 2
        }
        """;

    mockMvc.perform(post("/homework")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonOfCompletedHomework))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$", is("Домашняя работа загружена")));
  }

  @Test
  @DisplayName("Получение ДЗ по id должно возвращать соответствующее ДЗ")
  void getCompletedHomeworkTest() throws Exception {

    LoadedHomeworkDto loadedHomeworkDto = new LoadedHomeworkDto(
        1L,
        5L,
        "complete",
        2L
    );

    when(getLoadedHomework.execute(1L))
        .thenReturn(loadedHomeworkDto);

    mockMvc.perform(get("/homework/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.homeworkId", is(1)))
        .andExpect(jsonPath("$.studentId", is(5)))
        .andExpect(jsonPath("$.taskCompletionResult", is("complete")))
        .andExpect(jsonPath("$.homeworkForClassId", is(2)));

  }

}
