/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.Employee;
import entity.Grade;
import entity.Person;
import entity.PhDStudent;
import entity.Student;
import facade.EntityFactory;
import facade.Facade;
import java.util.Date;
import javax.persistence.EntityManager;

/**
 *
 * @author RolfMoikjær
 */
public class Tester {
    //Remember the Column dtype tells which entityclass the data in the DB.

    public static void main(String[] args) {
        EntityManager emf = EntityFactory.getInstance().createEntityManager();

        Date date = new Date();

        try (Facade facade = new Facade(emf)) {
            //addGrades
            Grade grade = new Grade();
            grade.setName("good grade");
            grade.setValue(10);
            facade.addGrade(grade);

            //addStudent
            Student student = new Student();
            student.setMatDate(date);
            student.setAge(34);
            student.setIsMarried(true);
            student.setMatNr(3);
            student.setfName("RAALF");
            student.setlName("PETERS");
            student.setGrade(grade);
            facade.addStudent(student);

            //addPerson
            Person person = new Person();
            person.setAge(23);
            person.setfName("sdfdsf");
            person.setlName("sdgsdf");
            facade.addPerson(person);

            //addEmployee
            Employee employee = new Employee();
            employee.setSoSecNr(222);
            employee.setAge(223);
            employee.setIsMarried(false);
            employee.setTaxClass("BIG");
            employee.setfName("Hansi");
            facade.addEmployee(employee);

            //addPhDStudent
            PhDStudent pstud = new PhDStudent();
            pstud.setAge(33);
            pstud.setfName("dssss");
            pstud.setTaxClass("sfds");
            facade.addPhDStudent(pstud);

            //fetchStudent
            Student student2 = facade.findStudent("2");
            System.out.println(student2.getfName() + " " + student2.getGrade().getName());
            //fetchPerson
            System.out.println(facade.findPerson("3").getfName());
            //fetchEmployee
            System.out.println(facade.findEmployee("4").getfName());
            //fetchPHD
            System.out.println(facade.findPHD("5").getAge());

        } catch (Exception e) {
            System.out.println("Problem" + e);
        }
    }
}
