package com.fabit.schoolapplication.infrastructure.persisnence.entity.journalofstudent;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "achievement")
public class AchievementEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "journal_by_discipline")
  private JournalByDisciplineEntity journalByDisciplineEntity;

  @Column(name = "date_of_lesson")
  private LocalDate dateOfLesson;

  @Column(name = "achievement_value")
  private String value;
}