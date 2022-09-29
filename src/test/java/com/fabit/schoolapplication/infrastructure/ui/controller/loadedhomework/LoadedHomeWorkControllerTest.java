package com.fabit.schoolapplication.infrastructure.ui.controller.loadedhomework;

import com.fabit.schoolapplication.application.usecase.scenario.loadedhomework.CompleteHomeworkUseCase;
import com.fabit.schoolapplication.application.usecase.scenario.loadedhomework.GetLoadedHomeworkUseCase;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.loadedhomework.LoadedHomework;
import com.fabit.schoolapplication.domain.loadedhomework.LoadedHomeworkId;
import com.fabit.schoolapplication.domain.student.StudentId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoadedHomeWorkControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  CompleteHomeworkUseCase completeHomeworkUseCase;

  @MockBean
  GetLoadedHomeworkUseCase getLoadedHomeworkUseCase;

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

    LoadedHomework loadedHomework = LoadedHomework.of(
      LoadedHomeworkId.of(1L), StudentId.of(5L), HomeworkForClassId.of(2L)
    );
    loadedHomework.uploadTaskCompletionResult("complete");

    when(getLoadedHomeworkUseCase.execute(LoadedHomeworkId.of(1L)))
      .thenReturn(loadedHomework);

    mockMvc.perform(get("/homework/1"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.homeworkId", is(1)))
      .andExpect(jsonPath("$.studentId", is(5)))
      .andExpect(jsonPath("$.taskCompletionResult", is("complete")))
      .andExpect(jsonPath("$.homeworkForClassId", is(2)));

  }

}
