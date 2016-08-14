/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testrunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRunner {

    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class[] lookInTheseClasses = {Person.class, PersonTest.class, SomeOther.class};
        runTests(lookInTheseClasses);
        System.out.println("Done running tests");
    }

    private static void runTests(Class[] arr) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        for (Class c : arr) {
            for (Method o : c.getDeclaredMethods()) {
                o.invoke(o, null);
            }
        }
    }

}
