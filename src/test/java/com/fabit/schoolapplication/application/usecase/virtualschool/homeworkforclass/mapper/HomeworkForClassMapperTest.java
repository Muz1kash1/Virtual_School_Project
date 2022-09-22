package com.fabit.schoolapplication.application.usecase.virtualschool.homeworkforclass.mapper;

import com.fabit.schoolapplication.application.mapper.HomeworkForClassMapper;
import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClass;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.homeworkforclass.dto.HomeworkForClassDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.homeworkforclass.HomeworkForClassEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;

@SpringBootTest public class HomeworkForClassMapperTest {

  @Autowired HomeworkForClassMapper homeworkForClassMapper;

  @DisplayName("Маппер Дз для класса работает корректно")
  @Test
  void homeworkForClassMapperTest() {
    HomeworkForClass homeworkForClass = HomeworkForClass.of(Discipline.COMPUTING, LocalDate.of(2012, 2, 2),
      SchoolClassId.of(1L), HomeworkForClassId.of(1L));
    homeworkForClass.setHomeworkText("test");

    HomeworkForClassEntity homeworkForClassEntity = new HomeworkForClassEntity();
    homeworkForClassEntity.setSchoolClassId(1L);
    homeworkForClassEntity.setDate(LocalDate.of(2012, 2, 2));
    homeworkForClassEntity.setId(1L);
    homeworkForClassEntity.setDiscipline(Discipline.COMPUTING);
    homeworkForClassEntity.setHomeworkTask("test");

    HomeworkForClassDto homeworkForClassDto = new HomeworkForClassDto(1L, Discipline.COMPUTING, "test", 1L,
      LocalDate.of(2012, 2, 2));

    Assertions.assertEquals(
      homeworkForClassMapper.mapHomeworkForClassToEntity(homeworkForClass).getSchoolClassId(),
      homeworkForClassEntity.getSchoolClassId()
    );
    Assertions.assertEquals(
      homeworkForClassMapper.mapHomeworkForClassToEntity(homeworkForClass).getHomeworkTask(),
      homeworkForClassEntity.getHomeworkTask()
    );

    Assertions.assertEquals(
      homeworkForClassMapper.mapDtoToHomeworkForClass(homeworkForClassDto).getDate(), homeworkForClass.getDate());
    Assertions.assertEquals(
      homeworkForClassMapper.mapDtoToHomeworkForClass(homeworkForClassDto).getDiscipline(),
      homeworkForClass.getDiscipline()
    );

    Assertions.assertEquals(
      homeworkForClassMapper.mapHomeworkForClassToDto(homeworkForClass).getSchoolClassId(),
      homeworkForClassDto.getSchoolClassId()
    );
    Assertions.assertEquals(
      homeworkForClassMapper.mapHomeworkForClassToDto(homeworkForClass).getId(),
      homeworkForClassDto.getId()
    );

    Assertions.assertEquals(
      homeworkForClassMapper.mapEntityToHomeworkForClass(homeworkForClassEntity).getDiscipline(),
      homeworkForClass.getDiscipline()
    );
    Assertions.assertEquals(
      homeworkForClassMapper.mapEntityToHomeworkForClass(homeworkForClassEntity).getTask(),
      homeworkForClass.getTask()
    );

  }

}
