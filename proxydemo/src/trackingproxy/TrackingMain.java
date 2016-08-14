/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trackingproxy;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kasper
 */
public class TrackingMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println("We get: " +new WhoKnockAtMyDoor().methodB() );
        
        ArrayList<String> al = new ArrayList<>();
        List<String> l = TrackingDecorator.track(al);
        l.add("Hans");
        l.add("Jens");
        if ( l.contains("Hans") ){
            System.out.println("BINGO");
        }
        //System.out.println("And what is class of the Proxy? " + l.getClass().getCanonicalName());
        //System.out.println("Which is a subclass of: " + l.getClass().getSuperclass().getCanonicalName() );
    }
    
    
    
}
