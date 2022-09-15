package com.fabit.schoolapplication.infrastructure.controller.student;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private StudentRepository studentRepository;

  @BeforeEach
  @DisplayName("Добавлени студента перед каждым тестом и проверка на правильность данных")
  public void addStudent() throws Exception {
    String json = """
        {
        "name":"Karl",
        "snils":{"numberView":"777777"},
        "birthCertificate":{"serial":"99998", "number":"111"},
        "passport":{"serial":"222", "number":" 1234567"},
        "birthday":"2022-09-12"
        }
        """;
    mockMvc.perform(post("/student/addStudent").contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isCreated())
        .andExpect(jsonPath("$.name", is("Karl"))).andExpect(jsonPath("$.snils", is("777777")))
        .andExpect(jsonPath("$.birthCertificate", is("99998 111")))
        .andExpect(jsonPath("$.birthday", is("2022-09-12")));
  }

  @AfterEach
  public void deleteStudentFromDb() {
    studentRepository.deleteAll();
  }

  @Test
  @DisplayName("Изменение свид. о рождении. и проверка на правильность выходных данных")
  void changeBirthCertificateStudent() throws Exception {
    String json = """
        {
        "name":"Karl",
        "snils":{"numberView":"777777"},
        "birthCertificate":{"serial":"111", "number":"111111"},
        "passport":{"serial":"222", "number":" 1234567"},
        "birthday":"2022-09-12"
        }
        """;
    mockMvc.perform(put("/student/changeBirthCertificate").contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("Karl"))).andExpect(jsonPath("$.snils", is("777777")))
        .andExpect(jsonPath("$.birthCertificate", is("111 111111")))
        .andExpect(jsonPath("$.birthday", is("2022-09-12")));
  }

  @Test
  @DisplayName("Изменение СНИЛС и проверка на правильность выходных данных")
  void changeSnilsStudent() throws Exception {
    String json = """
        {
        "name":"Karl",
        "snils":{"numberView":"333333"},
        "birthCertificate":{"serial":"99998", "number":"111"},
        "passport":{"serial":"222", "number":" 1234567"},
        "birthday":"2022-09-12"
        }
        """;
    mockMvc.perform(put("/student/changeSnils").contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("Karl"))).andExpect(jsonPath("$.snils", is("333333")));
  }

  @Test
  @DisplayName("Добавление паспорта студенту старше 14 лет и проверка на правильность выходных данных")
  void addPassportStudent15Years() throws Exception {
    String json = """
        {
        "name":"Karl",
        "snils":{"numberView":"777777"},
        "birthCertificate":{"serial":"99998", "number":"111"},
        "passport":{"serial":"222", "number":"1234567"},
        "birthday":"2007-09-12"
        }
        """;
    mockMvc.perform(put("/student/addPassport").contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("Karl"))).andExpect(jsonPath("$.snils", is("777777")))
        .andExpect(jsonPath("$.passport", is("222 1234567")));
  }

  @Test
  @DisplayName("Добавление паспорта студенту старше 14 лет, должен выбросить ошибку")
  void addPassportStudent10Years() {
    String json = """
        {
        "name":"Karl",
        "snils":{"numberView":"777777"},
        "birthCertificate":{"serial":"99998", "number":"111"},
        "passport":{"serial":"222", "number":"1234567"},
        "birthday":"2012-09-12"
        }
        """;
    assertThrows(NestedServletException.class, () -> mockMvc.perform(
        put("/student/addPassport").contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON).content(json)));
  }


  @Test
  @DisplayName("Удаление студента, должен удалить единственную запись в бд")
  void deleteStudent() throws Exception {
    List<StudentEntity> studentEntity = studentRepository.findAll();
    Assertions.assertEquals(1, studentEntity.size());
    mockMvc.perform(delete("/student/delete/{id}", studentEntity.get(0).getId()).contentType(
            MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(jsonPath("$", is("Ученик исключен из школы")));
    Assertions.assertEquals(0, studentRepository.findAll().size());
  }
}