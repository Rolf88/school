/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piggybank;

/**
 *
 * @author RolfMoikjær
 */
public class TestProxy {
    
    public static void main(String... args) {
//        Account a = new AccountBasedOnBalance();
//        a = new SecureAccountProxy(a);
//        
//        a.deposit(100.00);
//        a.withDraw(25.00);
//        System.out.println("You have: " + a.getBalance());
        
        Account executor = AccountProxyHandler.createProxy("username", "password");
        executor.deposit(125.0);
        executor.withDraw(50.0);
        System.out.println("Balance is: " + executor.getBalance());
    }
}
