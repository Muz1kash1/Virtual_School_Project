package com.fabit.schoolapplication.infrastructure.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.SchoolClassEntity;
import com.fabit.schoolapplication.infrastructure.usecase.schoolclass.AddStudentToSchoolClass;
import com.fabit.schoolapplication.infrastructure.usecase.schoolclass.CreateSchoolClass;
import com.fabit.schoolapplication.infrastructure.usecase.schoolclass.DeleteSchoolClass;
import com.fabit.schoolapplication.infrastructure.usecase.schoolclass.GetSchoolClass;
import com.fabit.schoolapplication.infrastructure.usecase.schoolclass.RemoveStudentFromSchoolClass;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@RequiredArgsConstructor
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class SchoolClassControllerTest {

  @Autowired
  MockMvc mockMvc;

  private final String mockJsonOfClass
      = "{\"schoolClassId\": 1, \"parallel\": 3, \"litera\": \"В\"}";

  // -------
  // ** GetSchoolClass usecase
  @MockBean
  GetSchoolClass getSchoolClass;

  @Test
  @DisplayName("Получение списка всех классов должно возвращать корректный объект.")
  void getAllSchoolClassesTest() throws Exception {
    SchoolClassEntity mockClass1 = SchoolClassEntity.of(
        SchoolClass.of(SchoolClassId.of(1L), SchoolClassName.of(2, "Б")));
    SchoolClassEntity mockClass2 = SchoolClassEntity.of(
        SchoolClass.of(SchoolClassId.of(2L), SchoolClassName.of(3, "В")));

    when(getSchoolClass.all())
        .thenReturn(List.of(mockClass1, mockClass2));

    mockMvc.perform(get("/school-class"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.[0].parallel", is(2)))
        .andExpect(jsonPath("$.[1].parallel", is(3)));
  }

  @Test
  @DisplayName("Получение школьного класса по id должно возвращать соответствующий класс.")
  void getSchoolClassById() throws Exception {
    SchoolClassEntity mockClass = SchoolClassEntity.of(
        SchoolClass.of(SchoolClassId.of(1L), SchoolClassName.of(2, "Б")));
    mockClass.setId(1L);

    when(getSchoolClass.byId(1L))
        .thenReturn(mockClass);

    mockMvc.perform(get("/school-class/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.litera", is("Б")));
  }

  @Test
  @DisplayName("Получение школьного класса по имени должно возвращать соответствующий класс.")
  void getSchoolClassByName() throws Exception {
    SchoolClassEntity mockClass = SchoolClassEntity.of(
        SchoolClass.of(SchoolClassId.of(1L), SchoolClassName.of(3, "В")));

    when(getSchoolClass.byName(3, "В"))
        .thenReturn(mockClass);

    mockMvc.perform(get("/school-class/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mockJsonOfClass))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.parallel", is(3)))
        .andExpect(jsonPath("$.litera", is("В")));
  }

  // -------
  // ** CreateSchoolClass usecase
  @MockBean
  CreateSchoolClass createSchoolClass;

  @Test
  @DisplayName("Создание школьного класса должно создавать школьный класс.")
  void createSchoolClassTest() throws Exception {
    mockMvc.perform(post("/school-class")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mockJsonOfClass))
        .andExpect(status().isCreated());
  }

  // -------
  // ** DeleteSchoolClass usecase
  @MockBean
  DeleteSchoolClass deleteSchoolClass;

  @Test
  @DisplayName("Удаление школьного класса должно удалять школьный класс.")
  void deleteSchoolClassTest() throws Exception {
    mockMvc.perform(delete("/school-class")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mockJsonOfClass))
        .andExpect(status().isNoContent());
  }

  // -------
  // ** AddStudentToSchoolClass usecase
  @MockBean
  AddStudentToSchoolClass addStudentToSchoolClass;

  @Test
  @DisplayName("Добавление студента в школьный класс должно работать корректно.")
  void addStudentToSchoolClassTest() throws Exception {
    mockMvc.perform(post("/school-class/1?studentId=1"))
        .andExpect(status().isNoContent());
  }

  // -------
  // ** RemoveStudentFromSchoolClass usecase
  @MockBean
  RemoveStudentFromSchoolClass removeStudentFromSchoolClass;

  @Test
  @DisplayName("Добавление студента в школьный класс должно работать корректно.")
  void removeStudentFromSchoolClassTest() throws Exception {
    mockMvc.perform(post("/school-class/1?studentId=1"))
        .andExpect(status().isNoContent());
  }

}
