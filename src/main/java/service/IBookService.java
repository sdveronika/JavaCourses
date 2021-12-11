package service;

import entity.Book;

import java.util.List;

public interface IBookService {
        List<Book> readAll();

        List<Book> readByAuthor(String authorName);

        void updateQuantityOfBookById(int id, int quantity);

        void deleteBookById(int id);

        void addBook(String title, String author, int quantity);
    }

