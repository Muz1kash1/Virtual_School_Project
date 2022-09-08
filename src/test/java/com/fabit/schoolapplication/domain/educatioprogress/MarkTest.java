package com.fabit.schoolapplication.domain.educatioprogress;

import com.fabit.schoolapplication.domain.FullName;
import com.fabit.schoolapplication.domain.Passport;
import com.fabit.schoolapplication.domain.Snils;
import com.fabit.schoolapplication.domain.teacher.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MarkTest {

    @Test
    @DisplayName("Можно создать валидную отметку 5")
    public void createMark_5_shouldPass() {
        Mark mark = new Mark("5");
        assertEquals("5", mark.getValue());
    }

    @Test
    @DisplayName("Нельзя создать невалидную отметку 0")
    public void createMark_0_shouldThrowIllegalArgumentException() {
        Throwable thrown =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> {
                            Mark mark = new Mark("0");
                        });
    }

    @Test
    @DisplayName("Нельзя создать невалидную отметку 2/1")
    public void createMark_2_1_shouldThrowIllegalArgumentException() {
        Throwable thrown =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> {
                            Mark mark = new Mark("2/1");
                        });
    }

    @Test
    @DisplayName("Нельзя создать невалидную отметку YY")
    public void createMark_YY_shouldThrowIllegalArgumentException() {
        Throwable thrown =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> {
                            Mark mark = new Mark("YY");
                        });
    }

    @Test
    @DisplayName("Можно создать валидную отметку 5/4")
    public void createMark_5_4_shouldPass() {
        Mark mark = new Mark("5/4");
        assertEquals("5/4", mark.getValue());
    }

    @Test
    @DisplayName("Можно создать валидную отметку НН")
    public void createMark_HH_shouldPass() {
        Mark mark = new Mark("НН");
        assertEquals("НН", mark.getValue());
    }

    @Test
    @DisplayName("Можно создать валидную отметку НН/УВ")
    public void createMark_HH_YB_shouldPass() {
        Mark mark = new Mark("НН/УВ");
        assertEquals("НН/УВ", mark.getValue());
    }
}