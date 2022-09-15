package com.fabit.schoolapplication.infrastructure.controller.teacher;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TeacherControllerTest {
  private static String createTeacher = """
          {
              "standingYears": 10,
              "fullName": {
                  "name": "Smirnov",
                  "surname": "Michael",
                  "patronymic": "Alexeevich"
              },
              "passport": {
                  "serial": 2008,
                  "number": 200931
              },
              "snils": {
                  "numberView": "127-328-591-72"
              },
              "active":true
          }
          """;
  private static String updateStandingYears = """
          {
              "teacherId": 1,
              "standingYears": 11
          }
          """;
  private static String deactivateTeacher = """
          {
              "teacherId": 1,
              "from": "2022-09-12",
              "to": "2022-09-22"
           }
          """;
  @Autowired MockMvc mockMvc;

  @Test
  void createTeacher() throws Exception {
    mockMvc.perform(post("/teacher/").contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON).content(createTeacher)).andExpect(status().isCreated())
            .andExpect(jsonPath("$.fullName", is("Smirnov Michael Alexeevich")))
            .andExpect(jsonPath("$.snils", is("127-328-591-72")));
  }

  @Test
  void changeStandingYears() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.put("/teacher/standing-years/").contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON).content(updateStandingYears)).andExpect(status().isAccepted())
            .andExpect(jsonPath("$.standingYears", is(11)));
  }
}
