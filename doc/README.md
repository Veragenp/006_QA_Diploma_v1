**Тестирование формы покупки тура**
Тестирование приложения, позволяющего купить тур двумя способами:
 - оплата по дебетовой карте;
 - выдача кредита по данным банковской карты.
Разработаны автотесты UI, а также выполнены проверки работы с базой данных.

**Начало работы**
1. Перейти по ссылке https://github.com/Veragenp/006_QA_Diploma_v1;
2. Нажать на кнопку Code (зеленого цвета), выбрать Download ZIP;
3. Распаковать архив.


**Prerequisites**

1. На ПК установить ПО Docker (для Windows 8, 7 - Docker Toolbox, для Windows 10 - Docker Desktop, 
   для MacOС - Docker Desktop);
2. На ПК установить ПО IntelliJ IDEA 2021 (Community Edition);
3. Установить браузер Google Chrome;

**Установка и запуск**

1. Запустить распакованный проект в ПО IntelliJ ID: вызвать панель gradle двойным нажатием кнопки Shift, внести в строку "build", нажать на Enter;
2. В командной строке внести "docker-compose up";
3. Запустить приложение при помощи команды: java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar
   (для базы MySQL) или при помощи команды: java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar
   (для базы Postgre);
4. Запустить тесты при помощи команды: gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app
   (для базы MySQL) или при помощи команды: gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app (для базы Postgre)


**Лицензия**

Лицензии не требуются

**Перечень документов:**
1. План работ;
2. Процедура запуска проекта;
3. Отчет по итогам тестирования;
4. Отчет по итогам автоматизации.





