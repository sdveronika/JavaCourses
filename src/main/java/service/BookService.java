package service;

import entity.Book;
import exception.BookNotFoundException;
import repository.BookRepository;
import repository.IBookRepository;

import java.util.List;

public class BookService implements IBookService {

    private static final IBookRepository bookRepository = new BookRepository();

    @Override
    public List<Book> readAll() {
        return bookRepository.readAll();
    }

    @Override
    public List<Book> readByAuthor(String authorName) {
        List<Book> books = bookRepository.readByAuthor(authorName);
        if (books.size() == 0) {
            throw new BookNotFoundException("Invalid author");
        }
        return books;
    }

    @Override
    public void updateQuantityOfBookById(int id,int quantity) {
        bookRepository.updateQuantityOfBookById(id,quantity);
    }

    @Override
    public void deleteBookById(int id) {
        bookRepository.deleteBookById(id);
    }

    @Override
    public void addBook(String title, String author, int quantity) {
bookRepository.addBook(title,author,quantity);
    }
}
