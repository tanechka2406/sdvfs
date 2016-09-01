package com.inwhite.controllers;

import com.inwhite.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Jeka on 21/06/2014.
 */
@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public String showMainPage(ModelMap model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "main";
    }


}
