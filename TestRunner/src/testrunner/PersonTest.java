/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testrunner;

@TestClass("Tests for class Person")
public class PersonTest {

    @TestMethod("Testing initialization")
    public Person initPerson() {
        Person p = new Person(25);
        return p;
    }

    @TestMethod("Calculating age")
    public int ageCalulation() {
        return initPerson().getAge();
    }

//    private setUpInState2(Person p) {
//    }
}
