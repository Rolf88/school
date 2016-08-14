package securitydemo;
import java.io.FileDescriptor;

public class NoFilesSecurityManager extends SecurityManager{
    @Override
    public void checkRead(FileDescriptor fd){
        super.checkRead(fd);
    }
}




