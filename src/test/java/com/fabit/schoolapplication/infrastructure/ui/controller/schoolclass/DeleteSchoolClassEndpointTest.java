package com.fabit.schoolapplication.infrastructure.ui.controller.schoolclass;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fabit.schoolapplication.application.usecase.scenario.schoolclass.DeleteSchoolClassUseCase;
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
public class DeleteSchoolClassEndpointTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  DeleteSchoolClassUseCase deleteSchoolClassUseCase;

  @Test
  @DisplayName("Удаление школьного класса должно удалять соответствующий класс")
  void deleteSchoolClassTest() throws Exception {

    final String jsonOfSchoolClass = """
        {
        "schoolClassId": 1,
        "parallel": 1,
        "litera": "А"
        }
        """;

    mockMvc.perform(delete("/school-class")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonOfSchoolClass))
        .andExpect(status().isNoContent());
  }

}
