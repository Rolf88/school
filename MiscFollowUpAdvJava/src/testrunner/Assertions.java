package testrunner;

public class Assertions {
    private static boolean testOK;
    static void resetForNewTest(){
        testOK = true;
    }
    static boolean testsWentOK(){
        return testOK;
    }
    
    public static void assertTrue(boolean test){
        if ( !test ) testOK = false;
    }
    
    public static void fail(){
        testOK = false;
    }
}
