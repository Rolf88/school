/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.TestData;
import java.util.List;

/**
 *
 * @author RolfMoikjær
 */
public class DataSerializer {

    private static JsonObject convertToJsonObject(TestData testData) {
        JsonObject obj = new JsonObject();

        obj.addProperty("fname", testData.getFname());
        obj.addProperty("lname", testData.getLname());
        obj.addProperty("street", testData.getStreet());
        obj.addProperty("city", testData.getCity());

        return obj;
    }

    public static String dataToJson(List<TestData> testDatas) {
        JsonArray jArr = new JsonArray();

        for (TestData testData : testDatas) {
            jArr.add(convertToJsonObject(testData));
        }
        return new Gson().toJson(jArr);
    }
}
