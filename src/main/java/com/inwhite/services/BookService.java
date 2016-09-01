package com.inwhite.services;

import com.inwhite.model.Book;

import java.util.List;

/**
 * Created by Jeka on 21/06/2014.
 */
public interface BookService {

    void increasePriceOfAllBooks(int delta);

    List<Book> getAllBooks();

    Book findBook(long id);

    void delete(long id);

    void update(Book book);

    void save(Book book);

    Book findBookByName(String name);
}
