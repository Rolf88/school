/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testrunner;
import static testrunner.Assertions.*;

@TestClass("It should run the tests in this class")
public class WillMyTestrunnerRunThis {
    
    @Test("This test succeeds")
    public void methodSuccess(){
        assertTrue( true );
    }
    @Test("This test will fail")
    public void methodFail(){
        fail();
    }
    
    @Test("This method will throw error")
    public void methodThrow(){
        throw new RuntimeException("uups");
    }
    
    public void methodNotExecuted(){
        System.out.println("You should not see this");
    }
    
    @Test("Testrunner should report error on the private test")
    private void privateMethod(){
        assertTrue( true );
    }
    
    
}
