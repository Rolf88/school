/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trackingproxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TrackingDecorator {
    
    public static<T> T track( T theRealThing){
        return (T) Proxy.newProxyInstance(
                theRealThing.getClass().getClassLoader(),
                theRealThing.getClass().getInterfaces(),//new Class[] { interf },
                new TrackingHandler( theRealThing )
            );
    }
    
    static class TrackingHandler implements InvocationHandler{
        private Object theRealThing;
        
        public TrackingHandler(Object realThing){
            this.theRealThing = realThing;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Entering: " + method.getName() );
            // delegate to the real object
            //method.invoke(TrackingDecorator.this.theRealThing, args);
            Object res =method.invoke(theRealThing, args);
            System.out.println("Leaving: " + method.getName());
            return res;
        }
        
    }
}
