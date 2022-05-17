package com.company.BookModels.dataRepositories;

import com.company.BookModels.Models.Book;

import java.util.ArrayList;

public class BookRepository {

    // this is the container holding all of the books
    public static ArrayList<Book> books = new ArrayList<>();

    // a helper method that adds a new book to our static books property
    public static void addBook(Book book) {
        books.add(book);
    }
}
