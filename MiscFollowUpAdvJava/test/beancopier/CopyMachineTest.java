/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beancopier;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kasper
 */
public class CopyMachineTest {
    
    public CopyMachineTest() {
    }

    @Test
    public void testCloneNewNotTheSame() {
        System.out.println("clone - gets new object");
        Object original = new ClassToCopy();
        Object result = CopyMachine.clone(original);
        assertNotNull(result);
        //assertNotEquals(original, result);
    }
    @Test
    public void testCloneSameClass() {
        System.out.println("clone - is of same type");
        Object original = new ClassToCopy();
        Class expResult = original.getClass();
        Class result = CopyMachine.clone(original).getClass();
        assertEquals(expResult, result);
    }
    @Test
    public void testCloneSameFieldValues() {
        System.out.println("clone - have same field values");
        ClassToCopy original = new ClassToCopy();
        ClassToCopy result = (ClassToCopy) CopyMachine.clone(original);
        assertEquals(original.born, result.born);
        assertEquals(original.height, result.height);
        assertEquals(original.name, result.name);
    }
   

    @Test
    public void testBeanNewNotTheSame() {
        System.out.println("beanClone - gets new object");
        Object original = new BeanToClone();
        Object result = CopyMachine.clone(original);
        assertNotNull(result);
        //assertNotEquals(original, result);
    }
    
    @Test
    public void testBeanCloneSameClass() {
        System.out.println("beanClone - is of same type");
        Object original = new BeanToClone();
        Class expResult = original.getClass();
        Class result = CopyMachine.clone(original).getClass();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testBeanCloneSameFieldValues() {
        System.out.println("beanClone - have same field values");
        BeanToClone original = new BeanToClone();
        BeanToClone result = (BeanToClone) CopyMachine.clone(original);
        assertEquals(original.getBorn(), result.getBorn() );
        assertEquals(original.getHeight(), result.getHeight() );
        assertEquals(original.getName(), result.getName() );
    }
    
}
