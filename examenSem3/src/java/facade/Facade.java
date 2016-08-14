/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import enity.Book;
import enity.EBook;
import enity.PaperBook;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author RolfMoikjær
 */
public class Facade implements Closeable {

    private final EntityManager entityManager;

    public Facade(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //Crud for book
    public void addBook(Book book) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(book);
        this.entityManager.getTransaction().commit();
    }

    public Book getBook(String id) {
        return this.entityManager.find(Book.class, Long.parseLong(id));
    }

    public List<Book> getAllBooks() {
        Query createQuery = this.entityManager.createQuery("SELECT b FROM Book b");

        return createQuery.getResultList();
    }

    public List<PaperBook> getAllPaperBooks() {
        Query createQuery = this.entityManager.createQuery("SELECT b FROM PaperBook b");

        return createQuery.getResultList();
    }

    public void deleteBook(Book book) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(book);
        this.entityManager.getTransaction().commit();
    }

    public void editBook(Book book) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(book);
        this.entityManager.getTransaction().commit();
    }

    //Crud for paperbook
    public void addPaperBook(PaperBook paperBook) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(paperBook);
        this.entityManager.getTransaction().commit();
    }

    public PaperBook getPaperBook(String id) {
        return this.entityManager.find(PaperBook.class, Long.parseLong(id));
    }

    public void deletePaperBook(PaperBook paperBook) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(paperBook);
        this.entityManager.getTransaction().commit();
    }

    public void editPaperBook(PaperBook paperBook) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(paperBook);
        this.entityManager.getTransaction().commit();
    }

    //Crud for EBook
    public void addEBook(EBook eBook) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(eBook);
        this.entityManager.getTransaction().commit();
    }

    public EBook getEBook(String id) {
        return this.entityManager.find(EBook.class, Long.parseLong(id));
    }

    @Override
    public void close() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
