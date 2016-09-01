package com.inwhite.model;

import com.inwhite.services.BookService;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Jeka on 21/06/2014.
 */
@Entity
@NamedQuery(name = "findAllBooks",query = "from Book")
public class Book implements Serializable {
    private long id;

    public void addPrice(int price) {
        setPrice(getPrice()+price);
    }

    @NotNull
    private String name;
    @Max(value = 300, message = "price must be less than 300")
    private int price;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hibernate-context.xml");
        BookService service = context.getBean(BookService.class);
        System.out.println();
    }
}
