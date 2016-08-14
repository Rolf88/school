package advjava02;


import static java.lang.System.*;

public class TellOMatic {
    
    private TellOMatic(){ } // Disable standard instantiation;
    
    public TellOMatic(String msg){
        out.println("I got: " + msg);
    }
    
    public static TellOMatic create(String msg){
        return new TellOMatic( msg );
    }
}
