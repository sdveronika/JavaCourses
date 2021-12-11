package repository;

import entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements IBookRepository {


    @Override
    public List<Book> readAll() {
        List<Book> books = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost/Books?useUnicode=true&serverTimezone=UTC",
                            "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Books.book");
            while (resultSet.next()) {
                Long bookId = Long.valueOf(resultSet.getString(1));
                String title = resultSet.getString(2);
                String author = resultSet.getString(3);
                Long quantity = Long.valueOf(resultSet.getString(4));
                books.add(new Book(bookId, title, author, quantity));
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> readByAuthor(String authorName) {
        List<Book> books = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Books?useUnicode=true&serverTimezone=UTC",
                    "root", "root");
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Books.book WHERE author = ?");
            preparedStatement.setString(1, authorName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long bookId = Long.valueOf(resultSet.getString(1));
                String title = resultSet.getString(2);
                String author = resultSet.getString(3);
                Long quantity = Long.valueOf(resultSet.getString(4));
                books.add(new Book(bookId, title, author, quantity));
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void updateQuantityOfBookById(int id,int quantity ) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Books?useUnicode=true&serverTimezone=UTC",
                    "root", "root");
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Books.book SET Book.quantity=? WHERE Book.ID=?;");
            preparedStatement.setInt(1,quantity);
            preparedStatement.setInt(2,id);
            int resultSet = preparedStatement.executeUpdate();
        }catch(ClassNotFoundException | SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void deleteBookById(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Books?useUnicode=true&serverTimezone=UTC",
                    "root", "root");
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Book WHERE Book.ID=?;");
            preparedStatement.setInt(1,id);
            int resultSet = preparedStatement.executeUpdate();
        }catch(ClassNotFoundException | SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void addBook(String title, String author, int quantity) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Books?useUnicode=true&serverTimezone=UTC",
                    "root", "root");
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Book (TitleBook, Author, Quantity) VALUES (?,?,?);");
            preparedStatement.setString(1,title);
            preparedStatement.setString(2,author);
            preparedStatement.setInt(3,quantity);
            int resultSet = preparedStatement.executeUpdate();
        }catch(ClassNotFoundException | SQLException exception){
            exception.printStackTrace();
        }

    }
}