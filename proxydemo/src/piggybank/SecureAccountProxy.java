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
class SecureAccountProxy implements Account {

    private Account real;
    private boolean isUserOk = true;
    
    public SecureAccountProxy(Account a) {
        real = a;
    }

    
    
    private void checkUser() {
        if(!isUserOk){ throw new RuntimeException("No, No Noo");}
   
    }

    @Override
    public void deposit(double amount) {
        checkUser();
        real.deposit(amount);
    }

    @Override
    public double getBalance() {
        checkUser();
        return real.getBalance();
    }

    @Override
    public double withDraw(double amount) {
        checkUser();
        return real.withDraw(amount);
    }

}
