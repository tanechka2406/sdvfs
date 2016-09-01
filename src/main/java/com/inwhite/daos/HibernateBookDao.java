package com.inwhite.daos;

import com.inwhite.model.Book;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Statistics;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jeka
 * Date: 6/23/13
 * Time: 12:35 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class HibernateBookDao implements BookDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Book> getAllBooksAbovePrice(int price) {
        Query query = entityManager.createQuery("select b from Book as b where b.price > :price");
//        Query query = entityManager.createNamedQuery("findBookMoreExpensiveThan");
        List<Book> resultList = query.setParameter("price", price).setFirstResult(0).setMaxResults(10).getResultList();
        return resultList;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public List<Book> getAllBooks() {
        Query query = entityManager.createQuery("from Book");
        List<Book> resultList = query.getResultList();
        return resultList;
    }

    public void addBook(Book book) {
        entityManager.persist(book);
    }

    @Override
    public void deleteBook(Book book) {
        entityManager.remove(book);
    }

    @Override
    public void updateBook(Book book) {
        entityManager.merge(book);
    }

    @Override
    public Book findBookByName(String name) {
        System.out.println("name = " + name);
        List<Book> books = entityManager.createQuery("select b from Book as b where b.name =:name").setParameter("name", name).getResultList();

        return !books.isEmpty() ? books.get(0) : null;
    }

    public Book findBook(Long id) {
        System.out.println("COMING FROM DAO!!!");
        Statistics stats = CacheManager.getInstance().getCache(Book.class.getName()).getStatistics();
        System.out.println(stats.getCacheHits());

        return entityManager.find(Book.class, id);
    }


}
