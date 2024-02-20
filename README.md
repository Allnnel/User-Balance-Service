# Отчет по заданию "User-Balance-Service":

Наш проект представляет собой учебную разработку микросервиса, предназначенного для управления пользователями и их балансами. В ходе работы над этим проектом мы изучаем создание RESTful API с применением Spring Boot и Spring Data JPA для взаимодействия с базой данных PostgreSQL.

# Технологии и сервисы:

Spring Boot: используется для быстрого и эффективного создания приложения.

Spring Data JPA: обеспечивает удобное взаимодействие с базой данных.

PostgreSQL: база данных, в которой хранятся данные о пользователях и их балансах.

RESTful API: обеспечивает гибкое и масштабируемое взаимодействие между клиентами и сервером.

# Пользователи:

Маппинг: {{url/users/}}

Реализованные запросы: GET, PUT, POST, DELETE

Пользователи хранятся в базе данных PostgreSQL с использованием Spring Data JPA.

# Функциональность:

Регистрация новых пользователей с указанием login (уникальный), password, email, birthDay, mobilePhone.

Изменение данных пользователей (email, birthDay, mobilePhone) по запросу с уникальным параметром login.

Получение данных о пользователе по параметру login.

Обработка ошибок, таких как повторная регистрация пользователя, запрос данных/изменение данных для несуществующего пользователя, и другие.

# Балансы:

Маппинг: {{url/balances/}}

Реализованные запросы: GET, PUT, POST, DELETE

Балансы пользователей хранятся в базе данных PostgreSQL с использованием Spring Data JPA.

# Функциональность:

Создание баланса для пользователя с уникальным параметром login.

Изменение баланса пользователя (увеличение или уменьшение).

Получение данных о балансе по параметру login.

Удаление баланса пользователя.

Обработка ошибок, включая отсутствие баланса, повторное создание баланса и другие.


# Структура:

- `src/main/java/com/example/controller`: классы контроллеров, которые обрабатывают HTTP-запросы от клиентов.
- `src/main/java/com/example/model`: классы, представляющие модели данных, такие как пользователи и балансы.
- `src/main/java/com/example/repository`: интерфейсы репозиториев для взаимодействия с базой данных.
- `src/main/java/com/example/response`: классы шаблонов ответа для взаимодействия с клиентами. 
- `src/main/java/com/example/service`: сервисы, которые реализуют бизнес-логику приложения.
- `src/main/java/com/example/exception`: классы исключений, которые могут быть выброшены в случае ошибок.
- `src/main/java/com/example/UserBalanceServiceApplication.java`: основной класс Spring Boot, который запускает приложение.
- `src/main/resources/application.properties`: файл настроек приложения, включая конфигурацию базы данных.
- `src/main/resources/db/migration`: скрипты миграции базы данных для создания таблиц и структуры данных.

`pom.xml` является файлом конфигурации Maven, который содержит зависимости и настройки проекта.

