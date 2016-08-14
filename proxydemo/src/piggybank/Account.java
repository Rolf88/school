package piggybank;

public interface Account {
    void deposit(double amount);

    double getBalance();

    double withDraw(double amount);
    
}
