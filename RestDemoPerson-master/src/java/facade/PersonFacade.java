package facade;

import deploy.DeploymentConfiguration;
import entity.Person;
import exceptions.PersonNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersonFacade implements IPersonFacade {

  private EntityManagerFactory emf;

  public PersonFacade(EntityManagerFactory e) {
    emf = e;
  }
  
  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  @Override
  public Person addPerson(Person p) {
    EntityManager em = getEntityManager();
    try {
      em.getTransaction().begin();
      em.persist(p);
      em.getTransaction().commit();
      return p;
    } finally {
      em.close();
    }
  }

  @Override
  public Person deletePerson(int id) throws PersonNotFoundException {
    EntityManager em = getEntityManager();
    try {
      Person p = em.find(Person.class, id);
      if(p == null){
        throw new PersonNotFoundException("No Person found with provided id");
      }
      em.getTransaction().begin();
      em.remove(p);
      em.getTransaction().commit();
      return p;
    } finally {
      em.close();
    }
  }

  @Override
  public Person getPerson(int id) throws PersonNotFoundException {
    EntityManager em = getEntityManager();
    try {
      Person p = em.find(Person.class,id);
       if(p == null){
        throw new PersonNotFoundException("No Person found with provided id");
      }
       return p;
    } finally {
      em.close();
    }
  }

  @Override
  public List<Person> getPersons() {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("select p from Person p").getResultList();
    } finally {
      em.close();
    }
  }

  @Override
  public Person editPerson(Person p) throws PersonNotFoundException {
    EntityManager em = getEntityManager();
    try {
      Person edited = em.find(Person.class,p.getId());
       if(edited == null){
        throw new PersonNotFoundException("No Person found with provided id");
      }
      edited.setfName(p.getfName());
      edited.setlName(p.getlName());
      edited.setPhone(p.getPhone());
      em.getTransaction().begin();
      em.merge(edited);
      em.getTransaction().commit();
      return edited;
    } finally {
      em.close();
    }
  }

}
