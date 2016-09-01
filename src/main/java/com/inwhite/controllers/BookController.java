package com.inwhite.controllers;

import com.inwhite.model.Book;
import com.inwhite.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Jeka on 21/06/2014.
 */
@Controller
@RequestMapping("/editBook")
public class BookController extends MainController {
    @Autowired
    private Validator validator;

    @Autowired
    private BookService bookService;

    @RequestMapping("/{id}")
    public String findBook(@PathVariable long id, ModelMap model) {
        model.addAttribute("book", bookService.findBook(id));
        return "editBook";
    }

    @RequestMapping("/find")
    public String findBookByName(ModelMap model, HttpServletRequest request) {
        Book foundBook = bookService.findBookByName(request.getParameter("name"));
        if (foundBook == null) {
            return "bookNotFound";
        }
        model.addAttribute("book", foundBook);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Iterator<? extends GrantedAuthority> iterator = userDetails.getAuthorities().iterator();
        while (iterator.hasNext()) {
            GrantedAuthority authority = iterator.next();
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                return "editBook";
            }
        }
        return "book";
    }

    @RequestMapping("/update")
    public String editBook(HttpServletRequest request, ModelMap model) {
        Book book = new Book();
        book.setName(request.getParameter("name"));
        book.setPrice(Integer.parseInt(request.getParameter("price")));
        book.setId(Long.parseLong(request.getParameter("id")));
        if (!valid(book)) {
            return "errorPage";
        }
        bookService.update(book);


        return "redirect:/main";
    }

    private boolean valid(Book book) {
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        System.out.println("validating...");
        for (ConstraintViolation<Book> violation : violations) {
            System.out.println("Validation error: ");
            System.out.println(violation.getMessage());
        }
        if (!violations.isEmpty()) {
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createBook() {
        return "createBook";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createBook(HttpServletRequest request, ModelMap model) {
        Book book = new Book();
        book.setName(request.getParameter("name"));
        book.setPrice(Integer.parseInt(request.getParameter("price")));
        bookService.save(book);

        return showMainPage(model);
    }
}
