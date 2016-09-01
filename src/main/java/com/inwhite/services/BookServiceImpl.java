package com.inwhite.services;

import com.inwhite.daos.BookDao;
import com.inwhite.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Jeka on 21/06/2014.
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao dao;

    @Override
    public void increasePriceOfAllBooks(int delta) {
        List<Book> books = getAllBooks();
        for (Book book : books) {
            book.addPrice(delta);
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return dao.getAllBooks();
    }

    @Override
    @Transactional(timeout = 3*60*1000,propagation = Propagation.REQUIRES_NEW)
    public Book findBook(long id) {
        return dao.findBook(id);
    }

    @Override
    public void delete(long id) {
        System.out.println("deleted "+id);
        dao.deleteBook(findBook(id));
    }

    @Override
    public void update(Book book) {
        System.out.println("updated");
        System.out.println("book = " + book);
        dao.updateBook(book);
    }

    @Override
    public void save(Book book) {
        System.out.println("book = " + book);
        System.out.println("saved");
        dao.addBook(book);
    }

    @Override
    public Book findBookByName(String name) {
        return dao.findBookByName(name);
    }
}
