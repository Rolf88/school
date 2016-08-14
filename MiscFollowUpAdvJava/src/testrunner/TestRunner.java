/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testrunner;
import beancopier.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestRunner {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Class[] candidates = {WillMyTestrunnerRunThis.class, String.class };
        List<Method> testMethods = new ArrayList<>();
        for (Class c : candidates){
            if ( c.getAnnotation(TestClass.class) != null ){
                for ( Method m : c.getMethods() ){
                    if ( m.getAnnotation( Test.class) != null ){
                        testMethods.add(m);
                    }
                }
            }
        }
        
        for( Method testMethod : testMethods ){
            try {
                System.out.println("-----------");
                System.out.println("Trying method: " + testMethod.getName() );
                Class cl = testMethod.getDeclaringClass();
                Object o = cl.newInstance();
                Assertions.resetForNewTest();
                testMethod.invoke(o, null);
                if ( Assertions.testsWentOK() )
                    System.out.println("GREEN " + testMethod.getName() 
                            + " - " + testMethod.getAnnotation(Test.class).value());
                else
                    System.out.println("YELLOW " + testMethod.getName() 
                            + " - " + testMethod.getAnnotation(Test.class).value());
            } catch (InstantiationException | IllegalAccessException ex) {
                System.out.println("Could not instantiate test object");
            } catch (IllegalArgumentException  ex) {
                System.out.println("Could not call test method: " + ex.getClass().getCanonicalName());
            } catch (Throwable anythingelse){
                System.out.println("RED test threw exception " + anythingelse.getMessage() );
                //anythingelse.printStackTrace();
            }
            
        }
        
    }
    
}
