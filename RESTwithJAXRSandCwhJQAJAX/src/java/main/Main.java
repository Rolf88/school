/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entity.Person;
import facade.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author RolfMoikjær
 */
public class Main {

    public static void main(String[] args) {
        Persistence.generateSchema("RESTwithJAXRSandCwhJQAJAXPU", null);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RESTwithJAXRSandCwhJQAJAXPU");

        try (PersonFacade facade = new PersonFacade(emf)) {
// Creates a new Person              
            Person person = new Person();
            person.setfName("Rolf");
            person.setlName("Moikjær");
            person.setPhone(45845645);
            //facade.addPerson(person);
            
            Person person2 = new Person();
            person2.setfName("Bjarke");
            person2.setlName("Thomsen");
            person2.setPhone(45845666);
            //facade.addPerson(person2);
            //Find and print out a Person
            System.out.println(facade.getPersons());
        }
    }
}
