package com.fabit.schoolapplication.infrastructure.controller.schoolclass;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fabit.schoolapplication.application.usecase.scenarious.schoolclass.AddStudentToSchoolClass;
import com.fabit.schoolapplication.application.usecase.scenarious.schoolclass.CreateSchoolClass;
import com.fabit.schoolapplication.application.usecase.scenarious.schoolclass.DeleteSchoolClass;
import com.fabit.schoolapplication.application.usecase.scenarious.schoolclass.GetSchoolClass;
import com.fabit.schoolapplication.application.usecase.scenarious.schoolclass.RemoveStudentFromSchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.SchoolClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.StudentInClassEntity;
import java.util.List;
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
public class SchoolClassControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  GetSchoolClass getSchoolClass;

  @MockBean
  CreateSchoolClass createSchoolClass;

  @MockBean
  DeleteSchoolClass deleteSchoolClass;

  @MockBean
  AddStudentToSchoolClass addStudentToSchoolClass;

  @MockBean
  RemoveStudentFromSchoolClass removeStudentFromSchoolClass;

  private final SchoolClassEntity schoolClassEntity1 = SchoolClassEntity.of(
      SchoolClass.of(
          SchoolClassId.of(1L),
          SchoolClassName.of(1, "А"),
          List.of(
              StudentId.of(1L),
              StudentId.of(2L)
          )
      )
  );
  private final SchoolClassEntity schoolClassEntity2 = SchoolClassEntity.of(
      SchoolClass.of(
          SchoolClassId.of(2L),
          SchoolClassName.of(2, "А"),
          List.of(
              StudentId.of(3L),
              StudentId.of(4L)
          )
      )
  );

  private final String jsonOfSchoolClass = """
      {
      "schoolClassId": 1,
      "parallel": 1,
      "litera": "А"
      }
      """;

  @Test
  @DisplayName("Получить все школьные классы должно возвращать 2 школьных класса")
  void getAllSchoolClassesTest() throws Exception {

    when(getSchoolClass.all())
        .thenReturn(List.of(schoolClassEntity1, schoolClassEntity2));

    mockMvc.perform(get("/school-class"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].parallel", is(1)))
        .andExpect(jsonPath("$.[1].parallel", is(2)))
        .andExpect(jsonPath("$.[0].litera", is("А")))
        .andExpect(jsonPath("$.[1].litera", is("А")));
  }

  @Test
  @DisplayName("Получение класса с id 2 должно возвращать соответствующий класс")
  void getSchoolClassByIdTest() throws Exception {

    when(getSchoolClass.byId(2L))
        .thenReturn(schoolClassEntity2);

    mockMvc.perform(get("/school-class/2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.parallel", is(2)));
  }

  @Test
  @DisplayName("Получение класса по имени должно возвращать соответствующий класс")
  void getSchoolClassByNameTest() throws Exception {

    when(getSchoolClass.byName(1, "А"))
        .thenReturn(schoolClassEntity1);

    mockMvc.perform(get("/school-class")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonOfSchoolClass))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.parallel", is(1)))
        .andExpect(jsonPath("$.litera", is("А")));
  }

  @Test
  @DisplayName("Создание школьного класса должно создавать соответствующий класс")
  void createSchoolClassTest() throws Exception {

    when(createSchoolClass.execute(1, "А"))
        .thenReturn(schoolClassEntity1);

    mockMvc.perform(post("/school-class")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonOfSchoolClass))
        .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("Удаление школьного класса должно удалять соответствующий класс")
  void deleteSchoolClassTest() throws Exception {
    mockMvc.perform(delete("/school-class")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonOfSchoolClass))
        .andExpect(status().isNoContent());
  }

  @Test
  @DisplayName("Добавление и удаление ученика в школьном классе должно вызывать соотв. юзкейсы")
  void addAndRemoveStudentToClassTest() throws Exception {

    when(addStudentToSchoolClass.execute(SchoolClassId.of(1), StudentId.of(2)))
        .thenReturn(StudentInClassEntity.of(1L, 2L));

    mockMvc.perform(post("/school-class/1?studentId=2")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonOfSchoolClass))
        .andExpect(status().isNoContent());

    mockMvc.perform(delete("/school-class/1?studentId=2")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonOfSchoolClass))
        .andExpect(status().isNoContent());
  }
}