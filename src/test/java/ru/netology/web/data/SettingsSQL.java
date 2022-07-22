package ru.netology.web.data;

import io.qameta.allure.Step;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.BeforeEach;

import java.sql.*;

public class SettingsSQL {

    public SettingsSQL() {

    }
    @Step("Получение статуса операции из таблицы payment_entity базы данных")
    public static String getStatusOperationFromDbPayment() {
        String url = System.getProperty("db.url");
        String statusSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();
        try (
                Connection conn = DriverManager.getConnection(//
                        url, "app", "pass")) {
            val status = runner.query(conn, statusSQL, new ScalarHandler<>());
            return (String) status;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return statusSQL;

    }
    @Step("Получение количества записей в таблице payment_entity")
    public static Long getAmountOffRecordFromDbPayment() {
        String url = System.getProperty("db.url");
        String countSQL = "SELECT COUNT(*) FROM payment_entity;";
        val runner = new QueryRunner();
        try (
                Connection conn = DriverManager.getConnection(//
                        url, "app", "pass")) {
            Long count = runner.query(conn, countSQL, new ScalarHandler<>());
            return count;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
       return null;
    }


    @Step("Получение статуса операции из таблицы credit_request_entity базы данных")
    public static String getStatusOperationFromDbCredit() {

        String url = System.getProperty("db.url");
        String statusSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();
        try (
                Connection conn = DriverManager.getConnection(//
                        url, "app", "pass")) {
            val status = runner.query(conn, statusSQL, new ScalarHandler<>());
            return (String) status;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return statusSQL;

    }

    @Step("Получение количества записей в таблице credit_request_entity")
    public static Long getAmountOffRecordFromDbCredit() {
        String url = System.getProperty("db.url");
        String countSQL = "SELECT COUNT(*) FROM credit_request_entity;";
        val runner = new QueryRunner();
        try (
                Connection conn = DriverManager.getConnection(//
                        url, "app", "pass")) {
            Long count = runner.query(conn, countSQL, new ScalarHandler<>());
            return count;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Step("Получение количества записей в таблице order_entity")
    public static Long getAmountOffRecordFromDbOrder() {
        String url = System.getProperty("db.url");
        String countSQL = "SELECT COUNT(*) FROM order_entity;";
        val runner = new QueryRunner();
        try (
                Connection conn = DriverManager.getConnection(//
                        url, "app", "pass")) {
            Long count = runner.query(conn, countSQL, new ScalarHandler<>());
            return count;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Step("Получение статуса операции из таблицы credit_request_entity базы данных")
    public static String getIdFromOrder() {

        String url = System.getProperty("db.url");
        String statusSQL = "SELECT id FROM order_entity ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();
        try (
                Connection conn = DriverManager.getConnection(//
                        url, "app", "pass")) {
            val status = runner.query(conn, statusSQL, new ScalarHandler<>());
            return (String) status;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return statusSQL;

    }

    @Step("Удаление всех данных таблицы из credit_request_entity")
    public static void cleanseTableCredit() {

        String url = System.getProperty("db.url");
        String statusSQL = "DELETE FROM credit_request_entity;";
        val runner = new QueryRunner();
        try (
                Connection conn = DriverManager.getConnection(//
                        url, "app", "pass")) {
            val status = runner.execute(conn, statusSQL, new ScalarHandler<>());

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    @Step("Удаление всех данных таблицы из payment_entity")
    public static void cleanseTablePayment() {

        String url = System.getProperty("db.url");
        String statusSQL = "DELETE FROM payment_entity;";
        val runner = new QueryRunner();
        try (
                Connection conn = DriverManager.getConnection(//
                        url, "app", "pass")) {
            val status = runner.execute(conn, statusSQL, new ScalarHandler<>());

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    @Step("Удаление всех данных таблицы из order_entity")
    public static void cleanseTableOrder() {

        String url = System.getProperty("db.url");
        String statusSQL = "DELETE FROM order_entity;";
        val runner = new QueryRunner();
        try (
                Connection conn = DriverManager.getConnection(//
                        url, "app", "pass")) {
            val status = runner.execute(conn, statusSQL, new ScalarHandler<>());

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }
}
