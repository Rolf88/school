/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Person;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author RolfMoikjær
 */
public class PersonFacade implements Closeable {

    private final EntityManager entityManager;

    public PersonFacade(EntityManagerFactory factory) {
        this.entityManager = factory.createEntityManager();
        this.entityManager.getTransaction().begin();
    }

    public Person addPerson(Person p) {
        this.entityManager.persist(p);

        return p;
    }

    public Person deletePerson(int id) {
        Person p = getPerson(id);
        this.entityManager.remove(p);
        return p;
    }

    public Person getPerson(int id) {
        Long longId = (long) id;
        return this.entityManager.find(Person.class, longId);
    }

    public List<Person> getPersons() {        
        List persons;
        
        Query createQuery = this.entityManager.createQuery("SELECT p FROM Person p");
        
        persons = createQuery.getResultList();
        
        return persons;
    }

    public Person editPerson(Person p) {
        int id = p.getId().intValue();
        
        Person waitP = getPerson(id);
        
        deletePerson(id);
        
        addPerson(waitP);
        
        return waitP;
    }

    @Override
    public void close() {
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
    }

}
