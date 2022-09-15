package com.fabit.schoolapplication.infrastructure.controller.student;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private StudentRepository studentRepository;
  private static String json = """
      {
      "name":"Karl",
      "snils":{"numberView":"777777"},
      "birthCertificate":{"serial":"99998", "number":"111"},
      "passport":{"serial":"222", "number":" 1234567"},
      "birthday":"2022-09-12"
      }
      """;

  @Test
  void addStudent() throws Exception {
    mockMvc.perform(post("/student/addStudent").contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isCreated())
        .andExpect(jsonPath("$.name", is("Karl"))).andExpect(jsonPath("$.snils", is("777777")))
        .andExpect(jsonPath("$.birthCertificate", is("99998 111")))
        .andExpect(jsonPath("$.birthday", is("2022-09-12")));
  }
}