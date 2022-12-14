package com.fabit.schoolapplication.infrastructure.ui.controller.teacher;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

  private final String createTeacherJson = """
      {
          "fullName": {
              "name": "Michael",
              "surname": "Smirnov",
              "patronymic": "Alexeevich"
          },
          "passport": {
              "serial": 2008,
              "number": 200931,
              "birthday": "1980-09-15"
          },
          "snils": {
              "numberView": "127-328-591-72"
          }
      }
      """;
  @Autowired
  TeacherRepository teacherRepository;
  @Autowired
  MockMvc mockMvc;

  @AfterEach
  public void after() {
    teacherRepository.deleteAll();
  }

  @Test
  @DisplayName("???????????????? ?????????????? ???????????? ???????????????? ?????????????????????????????? ????????????.")
  void createTeacher() throws Exception {
    mockMvc.perform(post("/teacher/")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(createTeacherJson))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.fullName.name", is("Michael")))
        .andExpect(jsonPath("$.snils.numberView", is("127-328-591-72")));
  }


  @Test
  @DisplayName("?????????????????? ?????????????? ???? id ???????????? ???????????????????? ???????????????????????????????? ??????????????.")
  void getTeacherTest() throws Exception {

    mockMvc.perform(post("/teacher/")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(createTeacherJson));

    TeacherEntity teacherEntity = teacherRepository.findAll().get(0);

    mockMvc.perform(get("/teacher/" + teacherEntity.getId()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.fullName.name", is("Michael")))
        .andExpect(jsonPath("$.snils.numberView", is("127-328-591-72")));
  }

  @Test
  void getAllTeachersTest() throws Exception {

    mockMvc.perform(post("/teacher/")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(createTeacherJson));

    mockMvc.perform(get("/teacher"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].snils.numberView", is("127-328-591-72")));
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