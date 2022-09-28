package com.fabit.schoolapplication.application.usecase.scenario.schoolclass;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.SchoolClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.SchoolClassRepository;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


@RequiredArgsConstructor
@SpringBootTest
@AutoConfigureMockMvc
public class GetSchoolClassTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  GetSchoolClassUseCase getSchoolClassUseCase;

  @MockBean
  SchoolClassRepository schoolClassRepository;

  @Test
  @DisplayName("Поиск школьных классов по пустой БД должен выбрасывать соответствующий exception")
  void getSchoolClassAllEmptyTest() {

    when(schoolClassRepository.findAll())
        .thenReturn(new ArrayList<>());

    Assertions.assertThrows(
        NoSuchElementException.class,
        () -> getSchoolClassUseCase.all(),
        "В БД нет школьных классов.");
  }

  @Test
  @DisplayName("Поиск школьных классов по несуществующему id должен выбрасывать exception")
  void getSchoolClassByIdEmptyTest() {
    when(schoolClassRepository.findById(any()))
        .thenReturn(Optional.empty());

    Assertions.assertThrows(
        NoSuchElementException.class,
        () -> getSchoolClassUseCase.byId(1),
        "Класса с id 1 не существует.");
  }

  @Test
  @DisplayName("Поиск школьных классов по имени должен возвращать соотв. класс")
  void getSchoolClassByName() {

    SchoolClassName className = SchoolClassName.of(6, "А");
    SchoolClass schoolClass = SchoolClass.of(SchoolClassId.of(1L), className);

    when(schoolClassRepository.findByParallelAndLitera(6, "А"))
        .thenReturn(SchoolClassEntity.of(schoolClass));

    Assertions.assertEquals(
        getSchoolClassUseCase.byName(className),
        schoolClass
    );
  }

}
