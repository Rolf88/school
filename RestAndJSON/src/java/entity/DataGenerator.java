/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import converters.DataSerializer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author RolfMoikjær
 */
public class DataGenerator {

    public DataGenerator() {
    }

    public static List<TestData> getData(int amount, String properties) {
        List<TestData> testDatas = new ArrayList();

        for (int i = 0; i < amount; i++) {
            testDatas.add(makeTestData(properties));
        }

//        for (int i = 0; i < testDatas.size(); i++) {
//            TestData finalData = testDatas.get(i);
//
//            System.out.println(finalData.getFname() + ", " + finalData.getLname() + ", "
//                    + finalData.getStreet() + ", " + finalData.getCity());
//        }
        return testDatas;
    }

    private static TestData makeTestData(String properties) {
        TestData testData = new TestData();

        List<String> fnames = new ArrayList();
        List<String> lnames = new ArrayList();
        List<String> streets = new ArrayList();
        List<String> cities = new ArrayList();

        fnames.add("Peter");
        fnames.add("Hans");
        fnames.add("Bo");
        fnames.add("Lars");
        fnames.add("Rolf");

        lnames.add("Hansen");
        lnames.add("Larsen");
        lnames.add("Harksen");
        lnames.add("Moikjær");
        lnames.add("Khan");

        streets.add("Rydtoften 20");
        streets.add("Lerdamsvej 16");
        streets.add("Rørmosen 2");
        streets.add("Glimosen 4");
        streets.add("Hejrevej 6");

        cities.add("Ballerup");
        cities.add("Herlev");
        cities.add("Skovlunde");
        cities.add("Odense");
        cities.add("Lyngby");

        Random r1 = new Random();

        if (properties.contains("fname")) {
            testData.setFname(fnames.get(r1.nextInt(4)));
        }
        if (properties.contains("lname")) {
            testData.setLname(lnames.get(r1.nextInt(4)));
        }
        if (properties.contains("street")) {
            testData.setStreet(streets.get(r1.nextInt(4)));
        }
        if (properties.contains("city")) {
            testData.setCity(cities.get(r1.nextInt(4)));
        } else {
            return testData;
        }
        return testData;
    }
}
