package com.inwhite.controllers;

import com.inwhite.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jeka on 21/06/2014.
 */
@Controller
@RequestMapping("/books")
public class ViewBookController extends MainController{
    @Autowired
    private BookService bookService;

    @Autowired
    private MainController mainController;

    @RequestMapping("/{id}")
    public String findBook(@PathVariable long id, ModelMap model){
        System.out.println("id = " + id);
        model.addAttribute("book", bookService.findBook(id));
        return "book";
    }

    @RequestMapping("/{id}/delete")
    public String deleteBook(@PathVariable long id, ModelMap model) {
        bookService.delete(id);

        return showMainPage(model);
    }
}






