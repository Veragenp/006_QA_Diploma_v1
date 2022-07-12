**Тестирование формы покупки тура**

**Начало работы**
1. Перейти по ссылке https://github.com/Veragenp/006_QA_Diploma_v1;
2. Нажать на кнопку Code (зеленого цвета), выбрать Download ZIP;
3. Распаковать архив.

**Prerequisites**

1. На ПК установить ПО Docker (для Windows 8, 7 - Docker Toolbox, для Windows 10 - Docker Desktop, 
   для MacOС - Docker Desktop);
2. На ПК установить ПО IntelliJ IDEA 2021 (Community Edition);
3. Установить браузер Google Chrome;
4. Установить Git.

**Установка и запуск**

1. Открыть распакованный проект в IntelliJ IDEA;
2. Нажать 2 раза нажать Ctrl на клавиатуре, в открывшейся панели Run Anything ввести "Gradle build"
3. В терминале ввести команду docker-compose up;
4. Для запуска сервиса с указанием пути к базе данных можно использовать следующие команды:
   для mysql
   `java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar`
   для postgresql
   `java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar`
   Запуск тестов также стоит выполнить с параметрами, указав путь к базе данных в командной строке:
   для mysql
   `gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"`
   для postgresql
   `gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"`


version: '3.7'
services:
mysqldb:
image: mysql:latest
ports:
- '3306:3306'
environment:
- MYSQL_RANDOM_ROOT_PASSWORD=yes
- MYSQL_DATABASE=app
- MYSQL_USER=app
- MYSQL_PASSWORD=pass
postgresdb:
image: postgres:latest
ports:
- '5432:5432'
environment:
- POSTGRES_DB=app
- POSTGRES_USER=app
- POSTGRES_PASSWORD=pass
node-app:
build: ./gate-simulator
ports:
- '9999:9999'
в корень проекта необходимо поместить папку gate-simulator, которая дана в репозитории с условием задачи и в неё добавить файл Dockerfile примерно такого содержания
FROM node:8.16.2-alpine
WORKDIR /opt/app
COPY . .
RUN npm install
CMD ["npm", "start"]
EXPOSE 9999
Для запуска сервиса с указанием пути к базе данных можно использовать следующие команды:
для mysql
`java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar`
для postgresql
`java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar`
Запуск тестов также стоит выполнить с параметрами, указав путь к базе данных в командной строке:
для mysql
`gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"`
для postgresql
`gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"`
Чтобы передать в сборку url базы данных можно использовать системные свойства. В файле build.gradle в секции test добавьте строку
systemProperty 'db.url', System.getProperty('db.url')
В классе работающем с базой данных значение переданного параметра можно будет получить с помощью
System.getProperty("db.url")
  
**Лицензия**

Лицензии не требуются


