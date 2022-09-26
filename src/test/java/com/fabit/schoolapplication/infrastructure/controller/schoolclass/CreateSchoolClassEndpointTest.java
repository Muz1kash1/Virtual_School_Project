package com.fabit.schoolapplication.infrastructure.controller.schoolclass;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fabit.schoolapplication.application.usecase.scenarious.schoolclass.CreateSchoolClassUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateSchoolClassEndpointTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  CreateSchoolClassUseCase createSchoolClassUseCase;

  @Test
  @DisplayName("Создание школьного класса должно создавать соответствующий класс")
  void createSchoolClassTest() throws Exception {

    final String jsonOfSchoolClass = """
        {
        "schoolClassId": 1,
        "parallel": 1,
        "litera": "А"
        }
        """;

    mockMvc.perform(post("/school-class")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonOfSchoolClass))
        .andExpect(status().isCreated());
  }

}
