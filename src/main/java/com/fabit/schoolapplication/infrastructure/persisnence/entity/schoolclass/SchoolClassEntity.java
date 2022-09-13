package com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass;

import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
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
import lombok.ToString;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

// Lombok
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
// --
@Entity
@Table(name = "school_class")
public class SchoolClassEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "parallel")
  private int parallel;

  @Column(name = "litera")
  private String litera;

  // -------
  // ** Публикация события

  @AfterDomainEventPublication
  protected void clearDomainEvents() {
    SchoolClass.domainEvents.clear();
  }

  @DomainEvents
  protected Collection<Object> domainEvents() {
    return Collections.unmodifiableList(SchoolClass.domainEvents);
  }

  // -------
  // ** Приватный конструктор

  /**
   * Приватный конструктор для фабричного метода
   *
   * @param id       - id
   * @param parallel - параллель (1-11)
   * @param litera   - литера (А-Я без ЪЬ)
   */
  private SchoolClassEntity(Long id, int parallel, String litera) {
    this.id = id;
    this.parallel = parallel;
    this.litera = litera;
  }

  // -------
  // ** Маппер

  /**
   * Фабричный метод для создания Entity
   *
   * @param id          - id для БД
   * @param schoolClass - доменная модель школьного класса
   * @return SchoolClassEntity
   */
  public static SchoolClassEntity of(SchoolClass schoolClass, Long id) {
    if (id > 0) {
      return new SchoolClassEntity(
          id,
          schoolClass.getSchoolClassName().getParallel(),
          schoolClass.getSchoolClassName().getLitera());
    } else {
      throw new IllegalArgumentException("Id должен быть больше 0");
    }
  }

}
