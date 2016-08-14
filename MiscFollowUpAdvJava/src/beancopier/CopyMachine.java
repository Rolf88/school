/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beancopier;
import java.lang.reflect.*;
import java.util.*;

/**
 *
 * @author kasper
 */
public class CopyMachine {

    public static Object getField(Object obj, String fieldName) {
        try {
            Field f = obj.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            return f.get(obj);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static <T> T otherOfSameType(T obj) {
        try {
            return (T) obj.getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            return null;
        }
    }

    public static void setField(Object obj, String fieldName, Object value) {
        try {
            Field f =obj.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(obj, value);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            // Forget about it...
        }
    }

    public static Object clone(Object original) {
        Object clone = otherOfSameType(original);
        for (Field f : original.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            final String fieldName = f.getName();
            Object fieldValue = getField(original, fieldName);
            setField(clone, fieldName, fieldValue);
        }
        return clone;
    }

    public static Object beanCloner(Object originalBean) {
        try {
            Method[] ma = originalBean.getClass().getMethods();
            List<String> accessors = new ArrayList<>();
            for (Method m : ma) {
                if (m.getName().startsWith("get") 
                        && m.getDeclaringClass() != Object.class) {
                    accessors.add(m.getName().replace("get", ""));
                }
            }
            Object clone = otherOfSameType(originalBean);
            for (String getSetPair : accessors) {
                Method getter = originalBean.getClass()
                        .getMethod("get" + getSetPair, null);
                Method setter = originalBean.getClass()
                        .getMethod("set" + getSetPair, getter.getReturnType());
                setter.invoke(clone,
                        getter.invoke(originalBean));
            }
            return clone;
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    
}
