package com.fabit.schoolapplication.application.usecase.scenarious.schoolclass;

import com.fabit.schoolapplication.application.usecase.access.schoolclass.SchoolClassService;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.SchoolClassEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

@RequiredArgsConstructor
public class CreateSchoolClassUseCase {

  final SchoolClassService schoolClassService;

  public SchoolClass execute(int parallel, String litera) {
    SchoolClassEntity schoolClassEntity = SchoolClassEntity.of(parallel, litera);
    return schoolClassService.persistSchoolClass(schoolClassEntity);
  }

  /**
   * Создать школьный класс со списком студентов.
   *
   * @param parallel   - параллель
   * @param litera     - литера
   * @param studentIds - список идентификаторов студентов
   * @return SchoolClass (созданный)
   */
  public SchoolClass execute(int parallel, String litera, List<Long> studentIds)
                             throws NotFoundException {

    SchoolClassEntity schoolClassEntity = SchoolClassEntity.of(parallel, litera);

    long schoolClassId = schoolClassService
        .persistSchoolClass(schoolClassEntity).getSchoolClassId().getValue();

    studentIds.forEach(stId -> schoolClassService.addStudentToClass(
        SchoolClassId.of(schoolClassId), StudentId.of(stId)));

    return schoolClassService.getById(schoolClassId);
  }

  public SchoolClass execute(SchoolClass schoolClass) {
    return schoolClassService.persistSchoolClass(schoolClass);
  }

}
