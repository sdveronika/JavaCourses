package controller;

import entity.Book;
import exception.BookNotFoundException;
import service.BookService;
import service.IBookService;

import java.util.List;
import java.util.Scanner;

public class BookController {
    private static final IBookService bookService = new BookService();

    public void mainMenu() {
        boolean flag = true;

        while (flag) {
            System.out.println("Select one of the following option:\n");
            System.out.println("1. Get all books");
            System.out.println("2. Get book by author");
            System.out.println("3. Add book"); // TODO
            System.out.println("4. Exit");
            int result = new Scanner(System.in).nextInt();
            switch (result) {
                case 1:
                    readAll();
                    break;
                case 2:
                    readByAuthor();
                    break;
                case 3:
                    addBook();
                    break;
                case 4:
                    System.out.println("Bye-bye my dear friend");
                    flag = false;
                    break;
                default:
                    System.err.println("Something went wrong ..");
            }
        }
    }

    public void readAll() {
        List<Book> books = bookService.readAll();
        viewBooks(books);
        boolean flag = true;
        while (flag) {
        System.out.println("Select one:");
        System.out.println("1. Update book");
        System.out.println("2. Delete book");
        System.out.println("3. Exit");
        int bookId = new Scanner(System.in).nextInt();
            switch (bookId) {
                case 1:
                    updateBook();
                    break;
                case 2:
                    deleteBook(bookId);
                    break;
                case 3:
                    flag = false;
                    break;
                default:
                    flag = false;
                    System.err.println("Something went wrong ..");
            }
        }
    }

    public void updateBook() {
        System.out.println("Write Book ID that you want to update");
        int id=new Scanner(System.in).nextInt();
        System.out.println("Enter new quantity");
        int newQuantity=new Scanner(System.in).nextInt();
        try {
            bookService.updateQuantityOfBookById(id,newQuantity);
        } catch (BookNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteBook(int bookId) {
        System.out.println("Write Book ID that you want to delete");
        int id=new Scanner(System.in).nextInt();
        try {
            bookService.deleteBookById(id);
        } catch (BookNotFoundException e) {
            System.err.println(e.getMessage());
        }

    }

    public void addBook(){
        System.out.println("What is the title of the book?");
        String title=new Scanner(System.in).nextLine();
        System.out.println("What is the author of the book?");
        String author=new Scanner(System.in).nextLine();
        System.out.println("What is quantity?");
        int quantity =new Scanner(System.in).nextInt();
        try {
            bookService.addBook(title,author,quantity);
        } catch (BookNotFoundException e) {
            System.err.println(e.getMessage());
        }

    }

    public void readByAuthor() {
        System.out.println("Write author: ");
        String authorName = new Scanner(System.in).nextLine();
        List<Book> books = null;
        try {
            books = bookService.readByAuthor(authorName);
        } catch (BookNotFoundException e) {
            System.err.println(e.getMessage());
        }
        viewBooks(books);
    }

    private void viewBooks(List<Book> books) {
        if (books != null) {
            int counter = 1;
            for (Book book : books) {
                System.out.println(counter + ". " + book);
                counter++;
            }
        }
    }
}
