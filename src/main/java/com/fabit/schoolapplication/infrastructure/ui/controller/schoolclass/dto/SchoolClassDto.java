package com.fabit.schoolapplication.infrastructure.ui.controller.schoolclass.dto;

import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
import com.fabit.schoolapplication.domain.student.StudentId;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SchoolClassDto implements Serializable {

  private long schoolClassId;
  private int parallel;
  private String litera;
  private List<StudentId> studentIds;

  /**
   * Factory method.
   *
   * @param schoolClass - доменная модель
   * @return SchoolClassDto
   */
  public static SchoolClassDto of(SchoolClass schoolClass) {
    return new SchoolClassDto(
        schoolClass.getSchoolClassId().getValue(),
        schoolClass.getSchoolClassName().getParallel(),
        schoolClass.getSchoolClassName().getLitera(),
        schoolClass.getStudents()
    );
  }

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
