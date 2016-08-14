/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testrunner;
import java.lang.annotation.*;

@Retention( RetentionPolicy.RUNTIME ) // keep annotations at run time
@Target( ElementType.METHOD ) // One can not specify class, so you write type
public @interface Test {
    public String value();
}
