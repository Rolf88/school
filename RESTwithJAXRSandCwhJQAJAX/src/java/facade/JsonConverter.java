/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Person;
import java.util.List;

/**
 *
 * @author RolfMoikjær
 */
public class JsonConverter {
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    
    public static Person getPersonFromJson(String js) {
        return gson.fromJson(js, Person.class);
    }

    public static String getJSONFromPerson(Person p) {
        
        return gson.toJson(p);
    }

    public static String getJSONFromPerson(List<Person> persons) {
        return gson.toJson(persons);
    }
}
