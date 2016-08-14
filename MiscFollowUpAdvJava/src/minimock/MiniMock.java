/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimock;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author kasper
 */
public class MiniMock<T> {
    Class<T> myInterface;
    T theProxy;

    public MiniMock(Class<T> onInterface) {
        myInterface = onInterface;
        ClassLoader cl = MiniMock.class.getClassLoader();
        Class[] interfaces = {myInterface};
        InvocationHandler handler = new MockRecorder();
        theProxy = (T)Proxy.newProxyInstance( cl, interfaces, handler );
        callOrder = new ArrayList<>();
    }

    T proxy() {
        return theProxy;
    }
    
    private class MethodInfo {
        String methodName;
        Object[] args;
        Object result;
    }
    private List<MethodInfo> callOrder;
    private MethodInfo current;
    //private static final Object myVOID = new Object();

    public void returns(Object result) {
        current.result = result;
        callOrder.add(current);
    }

    public void noReturn() {
        current.result = (Void)null;
        callOrder.add(current);
    }
    private class MockRecorder implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            current = new MethodInfo();
            current.methodName = method.getName();
            current.args = args;
            return nullFor( method.getReturnType() );
        }
    }
    private int nextUp;
    private boolean allGood;

    public void setPlayMode() {
        ClassLoader cl = this.getClass().getClassLoader();
        Class[] interfaces = {myInterface};
        InvocationHandler handler = new MockPlayer();
        theProxy = (T)Proxy.newProxyInstance( cl, interfaces, handler );
        nextUp = 0;
        allGood = true;
    }

    public boolean testWentOK() {
        return allGood && nextUp == callOrder.size();
    }
    private class MockPlayer implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ( nextUp >= callOrder.size()){
                allGood = false;
                return nullFor(method.getReturnType());
            }
                
            MethodInfo expected = callOrder.get(nextUp);
            boolean ok = expected.methodName.equals(method.getName())
                    && Arrays.equals(expected.args, args);
            nextUp++;
            if (ok)
                return expected.result;
            allGood = false;
            Object nullObject = expected.result == null?null:nullFor(expected.result.getClass());
            return nullObject;
        }
    }
    //Byte, Double, Float, Integer, Long, Short, Character, Boolean
    private static Object nullFor(Class cl){
        System.out.println("We are asked for null for: " + cl.getCanonicalName());
        if ( cl == byte.class) return Byte.valueOf((byte)0);
        if ( cl == double.class) return Double.valueOf(0);
        if ( cl == float.class) return Float.valueOf(0);
        if ( cl == int.class) return Integer.valueOf(0);
        if ( cl == long.class) return Long.valueOf(0);
        if ( cl == short.class) return Short.valueOf((short)0);
        if ( cl == char.class) return Character.valueOf((char)0);
        if ( cl == boolean.class) return Boolean.valueOf(false);
        if ( cl == Byte.class) return Byte.valueOf((byte)0);
        if ( cl == Double.class) return Double.valueOf(0);
        if ( cl == Float.class) return Float.valueOf(0);
        if ( cl == Integer.class) return Integer.valueOf(0);
        if ( cl == Long.class) return Long.valueOf(0);
        if ( cl == Short.class) return Short.valueOf((short)0);
        if ( cl == Character.class) return Character.valueOf((char)0);
        if ( cl == Boolean.class) return Boolean.valueOf(false);
        else return null;
    }
}
