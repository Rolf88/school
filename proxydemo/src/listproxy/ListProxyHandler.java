package listproxy;
import java.lang.reflect.*;
import java.util.*;

public class ListProxyHandler implements InvocationHandler {
    private boolean isAdmin;
    private final List realList;

    public ListProxyHandler(String user, String pwd) {
        if ("kasper".equals(user) && "sesam".equals(pwd))
            isAdmin = true;
        realList = new ArrayList();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (isAdmin) {
            Object ret = method.invoke(realList, args);
            if ( ret != realList) 
                return ret;
            else
                return this;
        } else {
            throw new Exception("You do not have the right to access this list");
        } 
    }
    
    public static List createProxy(String user, String pwd){
        ListProxyHandler handler = new ListProxyHandler( user, pwd );
        ClassLoader loader = handler.getClass().getClassLoader();
        Class[] interfaces = { List.class };
        return (List) Proxy.newProxyInstance(loader, interfaces, handler);
    }
}
