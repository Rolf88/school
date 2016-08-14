package facade;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Person;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author plaul1
 */
public class JSONConvertTest {
  
  public JSONConvertTest() {
  }

  String jsonPerson;
  Person person;
  @Before
  public void setUp() {
    person = new Person("Peter","Olsen","1234");
     jsonPerson = "{\"fName\":\"Peter\" , \"lName\" : \"Olsen\" , \"phone\" : \"1234\"}";
     //jsonPerson = new Gson().toJson(new Person("Peter","Olsen","234"));
  }
  
  @Test
  public void testGetPersonFromJson() {
    Person p = JSONConverter.getPersonFromJson(jsonPerson);
    assertEquals("Peter", p.getfName());
    System.out.println("ID: "+p.getId());
  }
  
  @Test
  public void testJsonFromPerson(){
    String json = JSONConverter.getJSONFromPerson(person);
    Person p = new Gson().fromJson(json, Person.class);
    assertEquals(p.getfName(),"Peter");
  }
  
  @Test
  public void testJsonFromPersons(){
    List<Person> persons = new ArrayList();
    persons.add(new Person("A","B","1"));
    persons.add(new Person("AA","BB","11"));
    persons.add(new Person("AAA","BBB","111"));
    String json = JSONConverter.getJSONFromPerson(persons);
    System.out.println("List: "+json);
    Type listType = new TypeToken<List<Person>>(){}.getType();
    List<Person> p = new Gson().fromJson(json, listType);
    assertEquals(p.size(), 3);
  }
}
