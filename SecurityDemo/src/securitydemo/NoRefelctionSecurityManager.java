package securitydemo;
import java.lang.reflect.ReflectPermission;
import java.security.Permission;

public class NoRefelctionSecurityManager extends SecurityManager {
    @Override
    public void checkPermission(Permission perm) {
        if ( perm instanceof ReflectPermission ) { // Just say no
            super.checkPermission(perm);
        }
    }
}





