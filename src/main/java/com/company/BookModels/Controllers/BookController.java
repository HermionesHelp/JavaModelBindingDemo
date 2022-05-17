package com.company.BookModels.Controllers;


import com.company.BookModels.Models.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

@Controller
@RequestMapping(value = "/book")
public class BookController {
    // this is the container holding all of the books
    public static ArrayList<Book> books = new ArrayList<>();

    // GET /book -> returns a JSON List of all the books
    @GetMapping
    public String getBooks(Model model) {
        model.addAttribute("books", books);
        return "bookList";
    }


    // GET /book/new -> returns an HTML form
    @GetMapping(value = "/new")
    public String addBookForm() {
        return "newBookForm";
    }

    // POST /book/new
    @PostMapping(value = "/new")
    public String addBook(@ModelAttribute Book newBook, Model model) {
        // We no longer have to create a hashMap representation of our book. We can use our Book model
//        HashMap<String, String> newBook = new HashMap<>();
//        newBook.put("title", title);
//        newBook.put("author", author);
//        newBook.put("ISBN", isbn);
        // calls helper method that adds the new HashMap to the static ArrayList of HashMaps
        addBook(newBook);
        model.addAttribute("bookName", newBook.getTitle());
        return "bookAdded";
    }

    @GetMapping(value = "/search")
    public String searchBooksForm(){
        return "filterBooksForm";
    }

    @PostMapping(value = "/search")
    public String searchBooks(Model model, @RequestParam String type,@RequestParam String keyword){
        ArrayList<Book> matchingBooks = new ArrayList<>();
        if (type.equals("author")){
            for(Book book : books) {
                if (book.getAuthor().equalsIgnoreCase(keyword.toLowerCase())) {
                    matchingBooks.add(book);
                }
            }
        } else if (type.equals("title")){
            for(Book book : books) {
                if(book.getTitle().equalsIgnoreCase(keyword.toLowerCase())) {
                    matchingBooks.add(book);
                }
            }
        }
        model.addAttribute("books", matchingBooks);
        return "filterBooks";
    }

    // a helper method that adds a new book to our static books property
    public static void addBook(Book book) {
        books.add(book);
    }
}
