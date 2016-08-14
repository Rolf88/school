/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piggybank;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RolfMoikjær
 */
public class AccountProxyHandler implements InvocationHandler {

    private boolean isUserOk;
    private final Account real;

    public AccountProxyHandler(String user, String pwd) {
        if ("username".equals(user) && "password".equals(pwd)) {
            isUserOk = true;
        }
        real = new AccountBasedOnBalance();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (isUserOk) {
            Object ret = method.invoke(real, args);
            if (ret != real) {
                return ret;
            } else {
                return this;
            }
        } else {
            throw new Exception("You do not have the right for this");
        }
    }

    public static Account createProxy(String user, String pwd) {
        AccountProxyHandler handler = new AccountProxyHandler(user, pwd);
        ClassLoader loader = handler.getClass().getClassLoader();
        Class[] interfaces = {Account.class};
        return (Account) Proxy.newProxyInstance(loader, interfaces, handler);
    }

}
