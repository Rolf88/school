package jpainheritancedemo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tha
 */
public class JPAInheritanceDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InheritancePU");
        EntityManager em = emf.createEntityManager();
        
        Item item1 = new Item();
        Item item2 = new Item();
        Item item3 = new Item();
        item1.setTitle("Item number 1");
        item2.setTitle("Item number 2");
        item3.setTitle("Item number 3");
        
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        book1.setISBN("ISBN2011");
        book2.setISBN("ISBN2012");
        book3.setISBN("ISBN2013");
        
        CD cd1 = new CD();
        CD cd2 = new CD();
        CD cd3 = new CD();
        cd1.setTitle("CD title 1");
        cd2.setTitle("CD title 2");
        cd3.setTitle("CD title 3");
        cd1.setNumbersOnCD("1990");
        cd2.setNumbersOnCD("1991");
        cd3.setNumbersOnCD("1992");
        
        List<Item> collection = new ArrayList();
        collection.add(item1);
        collection.add(item2);
        collection.add(item3);
        collection.add(book1);
        collection.add(book2);
        collection.add(book3);
        collection.add(cd1);
        collection.add(cd2);
        collection.add(cd3);
        try{
        em.getTransaction().begin();
        for (Item item : collection) {
            em.persist(item);
        }
        em.getTransaction().commit();
        } catch(Exception e){
            em.getTransaction().rollback();
        } finally{
            em.close();
        }
    }

}
