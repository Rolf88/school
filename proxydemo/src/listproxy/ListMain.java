/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listproxy;
import java.util.List;

/**
 *
 * @author kasper
 */
public class ListMain {
    
    public static void main(String... args){
        List l = ListProxyHandler.createProxy("kasper", "sesamy");
        l.add("Hans");
        l.add("Hassan");
        l.add("Heinz");
        System.out.println("List contains: " + l.size() + " elements.");
        
    }
    
}
