/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beancopier;

/**
 *
 * @author kasper
 */
public class BeanCopier {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        CopyMachine xerox = new CopyMachine();
        A myA = new A();
//        System.out.println("Xerox says: " + CopyMachine.getField(myA, "name"));
//        A otherA = CopyMachine.otherOfSameType(myA);
//        System.out.println("We got a: " + otherA.getClass().getCanonicalName());
//        CopyMachine.setField(otherA, "name", CopyMachine.getField(myA, "name"));
//        System.out.println("Xerox says: " + CopyMachine.getField(otherA, "name"));
        A other = (A) CopyMachine.clone(myA);
        System.out.println("Other has: " + CopyMachine.getField(other, "name"));
        System.out.println("Other has: " + CopyMachine.getField(other, "i"));
        System.out.println("Other has: " + (myA != other) );
        MyBean b = new MyBean();
        b.setBar(17);
        b.setFoo("Kasper");
        MyBean ob = (MyBean) CopyMachine.beanCloner(b);
        System.out.println("Cloned foo: " + ob.getFoo());
        System.out.println("Cloned bar: " + ob.getBar());
        
    }
    
}

class A {
    private String name = "Hans";
    Integer i = 17;
}

class MyBean {
    private String foo;
    private int bar;

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    public int getBar() {
        return bar;
    }

    public void setBar(int bar) {
        this.bar = bar;
    }
}
