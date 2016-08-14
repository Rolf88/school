/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimock;

/**
 *
 * @author kasper
 */
public class MockMain {
    public static void main(String... args){
        // Setup for testing the transfer method below
        MiniMock<Account> ma = new MiniMock( Account.class );
        ma.proxy().withDraw(500.0);
        ma.returns(500.0);
        ma.setPlayMode();
        MiniMock<Account> mb = new MiniMock( Account.class );
        mb.proxy().deposit(500.0);
        mb.noReturn();
        
        // test it
        mb.setPlayMode();
        transfer( ma.proxy(), mb.proxy(), 500.0 );
        
        // check if the test went OK - should be part of a test runner
        System.out.println("Mock ma reports: " + ma.testWentOK());
        System.out.println("Mock mb reports: " + mb.testWentOK());
    }
    
    static void transfer(Account a, Account b, Double amount){
        a.withDraw(amount);
        b.deposit(amount);
    }
    
}
