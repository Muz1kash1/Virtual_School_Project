package com.fabit.schoolapplication.infrastructure.controller.teacher;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
@WithMockUser
class TeacherControllerTest {

  @Autowired
  TeacherRepository teacherRepository;

  private final String createTeacherJson = """
      {
          "standingYears": 10,
          "fullName": {
              "name": "Smirnov",
              "surname": "Michael",
              "patronymic": "Alexeevich"
          },
          "passport": {
              "serial": 2008,
              "number": 200931,
              "birthday": "1980-09-15"
          },
          "snils": {
              "numberView": "127-328-591-72"
          },
          "active":true
      }
      """;

  @Autowired
  MockMvc mockMvc;

  @AfterEach
  public void after() {
    teacherRepository.deleteAll();
  }

  @Test
  @DisplayName("Создание учителя должно вызывать соответствующий юзкейс.")
  void createTeacher() throws Exception {
    mockMvc.perform(post("/teacher/")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(createTeacherJson))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.fullName", is("Smirnov Michael Alexeevich")))
        .andExpect(jsonPath("$.snils", is("127-328-591-72")));
  }

  @Test
  @DisplayName("Смена стажа у созданного учителя должна производиться верно.")
  void changeStandingYears() throws Exception {

    mockMvc.perform(post("/teacher/")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(createTeacherJson));

    long teacherId = teacherRepository.findAll().get(0).getId();
    final String updateStandingYearsJson
        = "{ \"teacherId\": " + teacherId + ", \"standingYears\": 11 }";

    mockMvc.perform(put("/teacher/standing-years/")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(updateStandingYearsJson))
        .andExpect(status().isAccepted())
        .andExpect(jsonPath("$.standingYears", is(11)));
  }


  @Test
  @DisplayName("Получение учителя по id должно возвращать соответствующего учителя.")
  void getTeacherTest() throws Exception {

    mockMvc.perform(post("/teacher/")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(createTeacherJson));

    TeacherEntity teacherEntity = teacherRepository.findAll().get(0);

    mockMvc.perform(get("/teacher/" + teacherEntity.getId()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.standingYears", is(10)))
        .andExpect(jsonPath("$.snils", is("127-328-591-72")));
  }

  @Test
  void getAllTeachersTest() throws Exception {

    mockMvc.perform(post("/teacher/")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(createTeacherJson));

    mockMvc.perform(get("/teacher"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].snils", is("127-328-591-72")));
  }

  @Test
  void deleteTeacherTest() throws Exception {

    long teacherCount;
    mockMvc.perform(post("/teacher/")

        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(createTeacherJson));

    long teacherId = teacherRepository.findAll().get(0).getId();

    teacherCount = teacherRepository.findAll().size();
    Assertions.assertEquals(1, teacherCount);

    mockMvc.perform(delete("/teacher/" + teacherId));

    teacherCount = teacherRepository.findAll().size();
    Assertions.assertEquals(0, teacherCount);
  }

}