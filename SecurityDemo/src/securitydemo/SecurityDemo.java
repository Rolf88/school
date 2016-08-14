package securitydemo;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SecurityDemo {
    public static void main(String[] args) {
        SecurityManager sm = System.getSecurityManager();
        if (sm == null) {
            System.out.println("No one to take care of us");
        } else {
            System.out.println("We are being watched by a: " + sm.getClass().getCanonicalName());
        }
        //System.setSecurityManager( new NoFilesSecurityManager() );
        System.setSecurityManager( new NoRefelctionSecurityManager() );
        System.out.println("We read from a file: " + readAFile());
        System.out.println("We find a method: " + getAMethod());
    }

    public static String readAFile() {
        try {
            return Files.readAllLines( Paths.get("test.txt") ).get(0);
        } catch (IOException ex) {
            ex.printStackTrace();
            return "S... happened";
        }
    }

    public static String getAMethod() {
        return SecurityDemo.class.getMethods()[1].getName();
    }
}
