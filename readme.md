# Учебный проект: Виртуальная школа

---

Совместный проект последних трёх недель стажёрской программы ФИТ.

#### Ссылка на задачу в Jira:
```
https://jira.fabit.ru/projects/INTERNSHIP/issues/INTERNSHIP-76?filter=allopenissues
```

---

### Перед запуском:

#### Докер образы

```
docker compose up
```

---

### Профили и БД:
1. #### default
```
jdbc:postgresql://localhost:5433/postgres
```
2. #### test
```
jdbc:postgresql://localhost:5434/postgres
```

---

### Архитектура приложения:

#### Ссылка на доску MIRO со спроектированными юзкейсами, ивентами и интерфейсами

```
https://miro.com/app/board/uXjVPZWfYvk=/
```

Приложение спроектировано, основываясь на Domain Driven Design и чистую архитектуру. **Доменная модель
разделена на агрегаты**:
1. Student (ученик)
2. Teacher (учитель)
3. SchoolClass (школьный класс)
4. Schedule (расписание школы на 1 день)
5. LoadedHomework (загруженное учеником ДЗ)
6. HomeworkForClass (ДЗ, заданное учителем)
7. AcademicAchievementOfStudent (академическая успеваемость ученика по школьным дисциплинам)

---

### Используемые технологии:

- Java
- Maven
- Spring Boot
- Spring Data JPA
- Spring Security Basic Authentication
- JUnit 5
- JUnit Jupiter
- Postgres
- Flyway
- Mapstruct
- Docker, docker-compose (v2)
- Gitlab CI
- OpenAPI
- AsyncAPI
- RabbitMQ
- Lombok
- Checkstyle (google)

---