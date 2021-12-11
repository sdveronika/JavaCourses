package main;

import controller.BookController;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        BookController bookController = new BookController();
        bookController.mainMenu();


    }
}
