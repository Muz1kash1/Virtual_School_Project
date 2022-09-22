package com.fabit.schoolapplication.infrastructure.controller.virtualschool.schoolclass.dto;

import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
import com.fabit.schoolapplication.domain.student.StudentId;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SchoolClassDto implements Serializable {

  private long schoolClassId;
  private int parallel;
  private String litera;
  private List<StudentId> studentIds;

  /**
   * Получить доменную модель из this.
   *
   * @return SchoolClass
   */
  public SchoolClass toDomain() {
    return SchoolClass.of(
        SchoolClassId.of(schoolClassId),
        SchoolClassName.of(parallel, litera),
        studentIds
    );
  }

}
