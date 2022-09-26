package com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass;

import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

// Lombok
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
// --
@Entity
@Table(name = "school_class")
public class SchoolClassEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "parallel")
  private int parallel;

  @Column(name = "litera")
  private String litera;

  // -------
  // ** Публикация события

  @AfterDomainEventPublication
  protected void clearDomainEvents() {
    SchoolClass.DOMAIN_EVENTS.clear();
  }

  @DomainEvents
  protected Collection<Object> domainEvents() {
    return Collections.unmodifiableList(SchoolClass.DOMAIN_EVENTS);
  }

  // -------
  // ** Приватный конструктор

  /**
   * Приватный конструктор для фабричного метода.
   *
   * @param parallel - параллель (1-11)
   * @param litera   - литера (А-Я без ЪЬ)
   */
  private SchoolClassEntity(int parallel, String litera) {
    this.parallel = parallel;
    this.litera = litera;
  }

  private SchoolClassEntity(long id, int parallel, String litera) {
    this.id = id;
    this.parallel = parallel;
    this.litera = litera;
  }

  // -------
  // ** Маппер

  /**
   * Factory method - Создание Entity.
   *
   * @param schoolClass - доменная модель школьного класса
   * @return SchoolClassEntity
   */
  public static SchoolClassEntity of(SchoolClass schoolClass) {
    return new SchoolClassEntity(
        schoolClass.getSchoolClassId().getValue(),
        schoolClass.getSchoolClassName().getParallel(),
        schoolClass.getSchoolClassName().getLitera()
    );
  }

  /**
   * Factory method - создание entity на основе параллели и литеры.
   *
   * @param parallel - параллель
   * @param litera   - литера
   * @return SchoolClassEntity
   */
  public static SchoolClassEntity of(int parallel, String litera) {
    return new SchoolClassEntity(parallel, litera);
  }

  /**
   * Маппинг this в доменную модель.
   *
   * @return SchoolClass
   */
  public SchoolClass toDomain() {
    return SchoolClass.of(SchoolClassId.of(id), SchoolClassName.of(parallel, litera));
  }

  @Override
  public String toString() {
    return parallel + "" + litera;
  }
}
