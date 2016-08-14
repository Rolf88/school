/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.DataGenerator;

/**
 *
 * @author RolfMoikjær
 */
public class Tester {

    public static DataGenerator dat;

    public Tester() {
        this.dat = new DataGenerator();
    }

    public static void main(String[] args) {
        System.out.println(DataGenerator.getData(25, "fname, lname, street, city"));
    }
}
