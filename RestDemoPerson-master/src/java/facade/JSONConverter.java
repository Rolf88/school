package facade;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Person;
import java.util.List;

public class JSONConverter {
  private static Gson gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
  //private static Gson gson = new Gson();
  
  public static Person getPersonFromJson(String js){ 
//    JsonObject jo = new JsonParser().parse(js).getAsJsonObject();
//    return new Person(jo.get("fName").getAsString(),jo.get("lName").getAsString(),jo.get("phone").getAsString());
    return gson.fromJson(js, Person.class);
  }  
  public static String getJSONFromPerson(Person p) {
    return gson.toJson(p);
  }  
  public static String getJSONFromPerson(List<Person> persons) {
    return gson.toJson(persons);
  }  
}
