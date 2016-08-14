/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piggybank;

/**
 *
 * @author kasper
 */
public class AccountBasedOnBalance implements Account {
    private double balance;
    
    @Override
    public void deposit( double amount){
        if ( amount < 0) throw new BankException("Cannot deposit negative", amount);
        balance += amount;        
    }
    
    @Override
    public double withDraw( double amount ){
        if ( balance < amount ) throw new BankException("Not sufficent funds", amount);
        balance -= amount;
        return amount;
    }
    
    @Override
    public double getBalance(){ return balance; }
    
    public class BankException extends RuntimeException {
        private final double amount;
        BankException(String msg, double amount){
            super( msg );
            this.amount = amount;
        }
        public double getAmount(){ return amount; }
        public double getBalance() { return balance; }
    }
    
}
