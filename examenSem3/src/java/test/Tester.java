/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import enity.Book;
import enity.EBook;
import enity.PaperBook;
import facade.EntityFactory;
import facade.Facade;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author RolfMoikjær
 */
public class Tester {

    public static void main(String[] args) {
        EntityManager emf = EntityFactory.getInstance().createEntityManager();

        try (Facade facade = new Facade(emf)) {

            //Create Books
            PaperBook paper = new PaperBook();
            paper.setAuthor("Dan Brown");
            paper.setInStock(200);
            paper.setPrice(100);
            paper.setTitle("Inferno");
            paper.setShippingWait(150);

            PaperBook paper2 = new PaperBook();
            paper2.setAuthor("HC Andersen");
            paper2.setInStock(300);
            paper2.setPrice(20);
            paper2.setTitle("Grimme Ælling");
            paper2.setShippingWait(300);

            Book book1 = new Book();
            book1.setAuthor("Ralf");
            book1.setPrice(150);
            book1.setTitle("Programming Genius in Making");

            EBook eBook = new EBook();
            eBook.setAuthor(null);
            eBook.setDownloadUrl("someURL");
            eBook.setPrice(150);
            eBook.setTitle("sdfsd");
            eBook.setSizeMB(10);

            //addBooks
            facade.addPaperBook(paper);
            facade.addBook(paper2);
            facade.addBook(book1);
            facade.addEBook(eBook);

            //Fetches a book
            PaperBook testBook = facade.getPaperBook("1");
            System.out.println("got book: " + testBook.getTitle() + " - " + testBook.getInStock() + " on stock!");

            List<Book> books = facade.getAllBooks();

            for (Book book : books) {
                System.out.println("book list names: " + book.getTitle() + " Author: " + book.getAuthor());
            }

            facade.deletePaperBook(paper2);

            List<PaperBook> paperBooks = facade.getAllPaperBooks();

            for (PaperBook testPaper : paperBooks) {
                System.out.println("paperbook list shipWaiting: " + testPaper.getShippingWait());
            }
        } catch (Exception e) {
            //System.out.println("Problem with Tester" + e);
        }
    }
}
