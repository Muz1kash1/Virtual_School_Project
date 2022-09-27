package com.fabit.schoolapplication.infrastructure.ui.controller.student;

import com.fabit.schoolapplication.application.usecase.access.student.StudentService;
import com.fabit.schoolapplication.domain.generalvalueobject.fullname.FullName;
import com.fabit.schoolapplication.domain.generalvalueobject.snils.Snils;
import com.fabit.schoolapplication.domain.student.BirthCertificate;
import com.fabit.schoolapplication.domain.student.Student;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class StudentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private StudentService studentRepository;
  private final Clock clock = Clock.fixed(Instant.parse("2022-09-15T00:00:00Z"), ZoneOffset.UTC);

  @BeforeEach
  @DisplayName("Добавление студента перед каждым тестом")
  public void initializeStudent() {
    studentRepository.deleteAll();
    Student student = Student.of(
      StudentId.of(1),
      FullName.of("Иванов", "Иван", "Иванович"),
      Snils.of("123-343-223-32"),
      BirthCertificate.of("1111", "999988",
        LocalDate.of(2010, 9, 15), clock
      )
    );
    studentRepository.save(student);
  }

  @Test
  @DisplayName("Добавление студента и проверка на правильность данных")
  public void addStudent() throws Exception {

    String json = """
      {
      "name":{"name":"Иванов","surname":"Иван","patronymic":"Иванович"},
      "snils":{"numberView":"123-343-223-32"},
      "birthCertificate":{"serial":"1111", "number":"999988","birthday":"2007-09-15"}
       }
      """;

    mockMvc.perform(
        post("/student")
          .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
          .content(json))
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.name", is("Иванов Иван Иванович")))
      .andExpect(jsonPath("$.snils", is("123-343-223-32")))
      .andExpect(jsonPath("$.birthCertificate", is("1111 999988")));
  }

  @Test
  @DisplayName("Изменение свид. о рождении. и проверка на правильность выходных данных")
  void changeBirthCertificateStudent() throws Exception {

    String json = """
      {
      "name":{"name":"Иванов","surname":"Иван","patronymic":"Иванович"},
      "snils":{"numberView":"123-343-223-32","birthday":"2007-09-15"},
      "birthCertificate":{"serial":"1112", "number":"111111","birthday":"2007-09-15"},
      "passport":{"serial":"2222", "number":"123456","birthday":"2007-09-15"}
      }
      """;

    mockMvc.perform(put("/student/change-birthcertificate")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON).content(json))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.serial", is("1112")))
      .andExpect(jsonPath("$.number", is("111111")))
      .andExpect(jsonPath("$.birthday", is("2007-09-15")));
  }

  @Test
  @DisplayName("Изменение СНИЛС и проверка на правильность выходных данных")
  void changeSnilsStudent() throws Exception {

    String json = """
      {
        "name":{"name":"Иванов","surname":"Иван","patronymic":"Иванович"},
        "snils":{"numberView":"443-343-223-32"},
        "birthCertificate":{"serial":"1111", "number":"999988","birthday":"2007-09-15"},
        "passport":{"serial":"2222", "number":"123456","birthday":"2007-09-15"}
      }
      """;

    mockMvc.perform(put("/student/change-snils")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(json))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.numberView", is("443-343-223-32")));
  }

  @Test
  @DisplayName("Добавление паспорта студенту старше 14 лет и проверка на правильность выходных данных")
  void addPassportStudent15Years() throws Exception {

    String json = """
      {
      "name":{"name":"Иванов","surname":"Иван","patronymic":"Иванович"},
      "snils":{"numberView":"123-343-223-32"},
      "birthCertificate":{"serial":"1111", "number":"999988","birthday":"2007-09-15"},
      "passport":{"serial":"2222", "number":"123456","birthday":"2007-09-15"}
       }
      """;

    mockMvc.perform(put("/student/add-passport")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(json))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.serial", is("2222")))
      .andExpect(jsonPath("$.number", is("123456")))
      .andExpect(jsonPath("$.birthday", is("2007-09-15")));
  }

  @Test
  @DisplayName("Добавление паспорта студенту 10 лет, должен выбросить ошибку IllegalArgument")
  void addPassportStudent10Years() throws Exception {

    String json = """
      {
      "name":{"name":"Иванов","surname":"Иван","patronymic":"Иванович"},
      "snils":{"numberView":"123-343-223-32"},
      "birthCertificate":{"serial":"1111", "number":"999988","birthday":"2012-09-15"},
      "passport":{"serial":"2222", "number":"123456","birthday":"2012-09-15"}
       }
      """;

    mockMvc.perform(put("/student/add-passport")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(json))
      .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$.title", is("Illegal Argument Exception")));
  }

  @Test
  @DisplayName("Удаление студента, должен удалить единственную запись в бд")
  void deleteStudent() throws Exception {

    List<StudentEntity> studentEntity = studentRepository.findAll();

    Assertions.assertEquals(1, studentEntity.size());

    mockMvc.perform(delete("/student/{id}", studentEntity.get(0).getId())
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .accept(MediaType.APPLICATION_PROBLEM_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", is("Ученик исключен из школы")));

    Assertions.assertEquals(0, studentRepository.findAll().size());
  }
}