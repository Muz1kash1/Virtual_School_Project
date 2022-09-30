package com.fabit.schoolapplication.infrastructure.persisnence.entity.journalofstudent;

import com.fabit.schoolapplication.domain.generalvalueobject.discipline.Discipline;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "journal_by_discipline")
public class JournalByDisciplineEntity {
  @Id
  private Long id;
  @ManyToOne
  @JoinColumn(name = "journal_of_student")
  private JournalOfStudentEntity journalOfStudentEntity;

  @Column(name = "discipline")
  @Enumerated(EnumType.STRING)
  private Discipline discipline;

  @OneToMany(mappedBy = "journalByDisciplineEntity")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<AchievementEntity> achievements = new ArrayList<>();
}