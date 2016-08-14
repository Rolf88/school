/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author RolfMoikjær
 */
public class Reflection {

    private static Object getField(Object obj, String fieldName) {
        
        
        return null;
    }

    private static Object callMethod(Object obj, String methodName) {
        Class oc = obj.getClass();

        try {
            Method mm = oc.getDeclaredMethod(methodName, String.class);

            return mm.invoke(obj, "TESTET");
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            return null;
        }
    }

    private static Object callMethod(Object obj, String methodName, Object... args) {
        Class oc = obj.getClass();

        Class[] paramTypes = new Class[args.length];
        for (int i = 0; i < paramTypes.length; i++) {
            paramTypes[i] = args[i].getClass();
        }

        try {
            Method mm = oc.getDeclaredMethod(methodName, paramTypes);

            return mm.invoke(obj, args);

        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        Person p = new Person();
        callMethod(p, "setName");
        callMethod(p, "setCity", "Ballerup");

        System.out.println(p.getName() + ", " + p.getCity());
    }

}

class Person {

    public String name;
    public String city;

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

}
