package facade;

import entity.Person;
import exceptions.PersonNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PersonFacadeTest {

  PersonFacade facade = new PersonFacade(Persistence.createEntityManagerFactory("pu_test"));
  private int idForOle = -1;

  public PersonFacadeTest() {
  }

  @Before
  public void setUp() {
    EntityManager em = facade.getEntityManager();
    try {
      em.getTransaction().begin();
      em.createQuery("delete from Person").executeUpdate();
      Person p = new Person("Ole", "Hansen", "1234");
      em.persist(p);
      em.persist(new Person("Hanne", "Hansen", "1234"));
      em.persist(new Person("Peter", "Olsen", "1234"));
      em.getTransaction().commit();
      idForOle = p.getId();
    } finally {
      em.close();
    }
  }

  @Test
  public void testAddPerson() throws PersonNotFoundException {
    Person newPerson = facade.addPerson(new Person("aaa", "bbb", "111"));
    assertEquals("aaa", newPerson.getfName());
    newPerson = facade.getPerson(newPerson.getId());
    assertEquals("aaa", newPerson.getfName());
  }

  @Test
  public void testDeletePerson() throws PersonNotFoundException {
    facade.deletePerson(idForOle);
    assertEquals(facade.getPersons().size(), 2);
  }

  @Test
  public void testGetPerson() throws PersonNotFoundException {
    Person p = facade.getPerson(idForOle);
    assertEquals(p.getfName(), "Ole");
  }

  @Test
  public void testGetPersons() {
    assertEquals(facade.getPersons().size(), 3);
  }

  @Test
  public void testEditPerson() throws PersonNotFoundException {
    Person ole = facade.getPerson(idForOle);
    ole.setPhone("555555");
    ole = facade.editPerson(ole);
    assertEquals(ole.getPhone(), "555555");
    ole = facade.getPerson(idForOle);
    assertEquals(ole.getPhone(), "555555");
  }

}
