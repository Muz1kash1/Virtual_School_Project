package com.fabit.schoolapplication.application.usecase.scenarious.schoolclass;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.fabit.schoolapplication.application.usecase.scenarious.schoolclass.GetSchoolClassUseCase;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.SchoolClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.StudentInClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.SchoolClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentInClassRepository;
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
public class GetSchoolClassByStudentIdInTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  GetSchoolClassUseCase getSchoolClassUseCase;

  @MockBean
  StudentInClassRepository studentInClassRepository;

  @MockBean
  SchoolClassRepository schoolClassRepository;

  @Test
  @DisplayName("Получение школьного класса ученика должно возвращать корректный класс")
  void getSchoolClassByStudentIdInTest() {

    Optional<SchoolClassEntity> mockClass = Optional.of(SchoolClassEntity.of(
        SchoolClass.of(SchoolClassId.of(1L), SchoolClassName.of(4, "А")))
    );

    when(studentInClassRepository.findByStudentId(any()))
        .thenReturn(StudentInClassEntity.of(1L, StudentId.of(5L).getValue()));
    when(schoolClassRepository.findById(any()))
        .thenReturn(mockClass);

    SchoolClass resultedClass = getSchoolClassUseCase.getByStudentId(1L);

    SchoolClassName schoolClassName = resultedClass.getSchoolClassName();

    Assertions.assertEquals(
        "4А",
        schoolClassName.getParallel() + schoolClassName.getLitera()
    );
  }

  @Test
  @DisplayName("Поиск класса ученика при отсутствии ученика должно выбрасывать NoSuchElement")
  void noClassExceptionTest() {
    when(studentInClassRepository.findByStudentId(any()))
        .thenReturn(StudentInClassEntity.of(1L, StudentId.of(5L).getValue()));
    when(schoolClassRepository.findById(any()))
        .thenReturn(Optional.empty());

    Assertions.assertThrows(NoSuchElementException.class,
        () -> getSchoolClassUseCase.getByStudentId(999L)
    );
  }


}
