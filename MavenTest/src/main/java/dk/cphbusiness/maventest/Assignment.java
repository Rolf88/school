/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.maventest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RolfMoikjær
 */
public class Assignment {

    private static void assign() throws IOException {
        File file = new File("/Users/RolfMoikjær/testData/data.txt");

        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line = in.readLine();
            List<Integer> totals = new ArrayList<>();
            do {
                if (line == null || line.isEmpty()) {
                    break;
                }
                String[] parts = line.split(";");
                int subtotal = 0;
                for (int index = 0; index < parts.length; index++) {
                    subtotal += Integer.parseInt(parts[index]);
                }
                totals.add(subtotal);
            } while (true);
            int grandtotal = 0;
            for (int total : totals) {
                grandtotal += total;
                System.out.println(total);
            }
            System.out.println("-------------");
            System.out.println(grandtotal);
        }

    }

    public static void main(String[] args) throws IOException {
        assign();
    }
}
