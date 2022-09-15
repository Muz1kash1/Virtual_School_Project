package com.fabit.schoolapplication.infrastructure.usecase.schoolclass;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.fabit.schoolapplication.infrastructure.persisnence.repository.SchoolClassRepository;
import java.util.List;
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
  GetSchoolClass getSchoolClass;

  @MockBean
  SchoolClassRepository schoolClassRepository;

  @Test
  @DisplayName("Поиск школьных классов по пустой БД должен выбрасывать соответствующий exception")
  void getSchoolClassAll() {
    when(schoolClassRepository.findAll())
        .thenReturn(List.of());
    Assertions.assertThrows(
        NoSuchElementException.class,
        () -> getSchoolClass.all(),
        "В БД нет школьных классов.");
  }

  @Test
  @DisplayName("Поиск школьных классов по несуществующему id должен выбрасывать exception")
  void getSchoolClassById() {
    when(schoolClassRepository.findById(any()))
        .thenReturn(Optional.empty());
    Assertions.assertThrows(
        NoSuchElementException.class,
        () -> getSchoolClass.byId(1),
        "Класса с id 1 не существует.");
  }

  @Test
  @DisplayName("Поиск школьных классов по несуществующему name должен выбрасывать exception")
  void getSchoolClassByName() {
    when(schoolClassRepository.findByParallelAndLitera(1, "А"))
        .thenReturn(null);
    Assertions.assertNull(getSchoolClass.byName(1, "А"));
  }

}
