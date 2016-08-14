/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Employee;
import entity.Grade;
import entity.Person;
import entity.PhDStudent;
import entity.Student;
import java.io.Closeable;
import java.io.IOException;
import javax.persistence.EntityManager;

/**
 *
 * @author RolfMoikjær
 */
public class Facade implements Closeable {

    private final EntityManager entityManager;

    public Facade(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Person findPerson(String id) {

        return this.entityManager.find(Person.class, Long.parseLong(id));
    }

    public void addPerson(Person person) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(person);
        this.entityManager.getTransaction().commit();
    }

    public void editPerson(Person person) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(person);
        this.entityManager.getTransaction().commit();
    }

    public void deletePerson(Person person) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(person);
        this.entityManager.getTransaction().commit();
    }

    public Student findStudent(String id) {

        return this.entityManager.find(Student.class, Long.parseLong(id));
    }

    public void addStudent(Student student) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(student);
        this.entityManager.getTransaction().commit();
    }

    public void editStudent(Student student) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(student);
        this.entityManager.getTransaction().commit();
    }

    public void deleteStudent(Student student) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(student);
        this.entityManager.getTransaction().commit();
    }

    public Employee findEmployee(String id) {
        return this.entityManager.find(Employee.class, Long.parseLong(id));
    }

    public void addEmployee(Employee employee) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(employee);
        this.entityManager.getTransaction().commit();
    }

    public void editEmployee(Employee employee) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(employee);
        this.entityManager.getTransaction().commit();
    }

    public void deleteEmployee(Employee employee) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(employee);
        this.entityManager.getTransaction().commit();
    }

    public void addPhDStudent(PhDStudent phdstudent) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(phdstudent);
        this.entityManager.getTransaction().commit();
    }

    public PhDStudent findPHD(String id) {
        return this.entityManager.find(PhDStudent.class, Long.parseLong(id));
    }

    public void addGrade(Grade grade) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(grade);
        this.entityManager.getTransaction().commit();
    }

    public Grade findGrade(String id) {
        return this.entityManager.find(Grade.class, Long.parseLong(id));
    }

    @Override
    public void close() throws IOException {
        this.entityManager.close();
    }

}
