package com.fabit.schoolapplication.infrastructure.ui.exceptionhandler;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fabit.schoolapplication.infrastructure.ui.controller.student.DeleteStudentEndpoint;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class RestResponseEntityExceptionHandlerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  DeleteStudentEndpoint deleteStudentEndpoint;

  @Test
  void handleIllegalArgumentExceptionTest() throws Exception {

    when(deleteStudentEndpoint.deleteStudent(1))
        .thenThrow(new IllegalArgumentException());

    mockMvc.perform(delete("/student/1"))
        .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.title", is("Illegal Argument Exception")));
  }

  @Test
  void handleIllegalStateExceptionTest() throws Exception {

    when(deleteStudentEndpoint.deleteStudent(2))
        .thenThrow(new IllegalStateException());

    mockMvc.perform(delete("/student/2"))
        .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
        .andExpect(status().isConflict())
        .andExpect(jsonPath("$.title", is("Illegal state")));
  }

  @Test
  void handleNoSuchAndNoFoundElementExceptionTest() throws Exception {

    when(deleteStudentEndpoint.deleteStudent(3))
        .thenThrow(new NoSuchElementException());

    mockMvc.perform(delete("/student/3"))
        .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.title", is("Not Found Exception")));
  }

  @Test
  void handleAnyElseExceptionsTest() throws Exception {

    when(deleteStudentEndpoint.deleteStudent(4))
        .thenThrow(new RuntimeException());

    mockMvc.perform(delete("/student/4"))
        .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
        .andExpect(status().is5xxServerError())
        .andExpect(jsonPath("$.title", is("Internal server error")));
  }
}