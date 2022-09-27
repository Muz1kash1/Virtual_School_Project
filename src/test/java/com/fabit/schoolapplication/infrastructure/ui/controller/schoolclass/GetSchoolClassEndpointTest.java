package com.fabit.schoolapplication.infrastructure.ui.controller.schoolclass;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fabit.schoolapplication.application.usecase.scenario.schoolclass.GetSchoolClassUseCase;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.SchoolClassEntity;
import java.util.ArrayList;
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
public class GetSchoolClassEndpointTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  GetSchoolClassUseCase getSchoolClassUseCase;

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

  @Test
  @DisplayName("Получить все школьные классы должно возвращать 2 школьных класса")
  void getAllSchoolClassesTest() throws Exception {

    List<SchoolClass> schoolClasses = new ArrayList<>();
    schoolClasses.add(schoolClassEntity1.toDomain());
    schoolClasses.add(schoolClassEntity2.toDomain());

    when(getSchoolClassUseCase.all())
        .thenReturn(schoolClasses);

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

    when(getSchoolClassUseCase.byId(2))
        .thenReturn(schoolClassEntity2.toDomain());

    mockMvc.perform(get("/school-class/2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.parallel", is(2)));
  }

  @Test
  @DisplayName("Получение класса по имени должно возвращать соответствующий класс")
  void getSchoolClassByNameTest() throws Exception {

    final String jsonOfSchoolClass = """
        {
        "schoolClassId": 1,
        "parallel": 1,
        "litera": "А"
        }
        """;

    when(getSchoolClassUseCase.byName(1, "А"))
        .thenReturn(schoolClassEntity1.toDomain());

    mockMvc.perform(get("/school-class")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonOfSchoolClass))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.parallel", is(1)))
        .andExpect(jsonPath("$.litera", is("А")));
  }

  @Test
  @DisplayName("Получение школьного класса по id студента должно возвращать верный класс")
  void getSchoolClassByStudentIdTest() throws Exception {

    when(getSchoolClassUseCase.getByStudentId(1))
        .thenReturn(schoolClassEntity1.toDomain());

    mockMvc.perform(get("/school-class/student/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.parallel", is(1)))
        .andExpect(jsonPath("$.schoolClassId", is(1)));
  }

}
