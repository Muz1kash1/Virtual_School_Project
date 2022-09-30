package com.fabit.schoolapplication.infrastructure.persisnence.entity.journalofstudent;

import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "journal_of_student")
@Data
public class JournalOfStudentEntity {

  @Id
  private Long id;
  @OneToOne
  @JoinColumn(name = "student_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private StudentEntity student;

  @OneToMany(mappedBy = "journalOfStudentEntity")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<JournalByDisciplineEntity> journalByDisciplineEntityList =
    new ArrayList<>();
}
