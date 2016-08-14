package rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author plaul1
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> resources = new java.util.HashSet<>();
    addRestResourceClasses(resources);
    return resources;
  }

  private void addRestResourceClasses(Set<Class<?>> resources) {
    resources.add(exception.AllExceptionMapper.class);
    resources.add(exception.NotFoundExceptionMapper.class);
    resources.add(exceptions.PersonNotFoundExceptionMapper.class);
    resources.add(rest.PersonResource.class);
  }
  
}
