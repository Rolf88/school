package testrunner;

import java.lang.annotation.*;

@Retention( RetentionPolicy.RUNTIME ) // keep annotations at run time
@Target( ElementType.TYPE ) // One can not specify class, so you write type
public @interface TestClass {
    public String value();
}
