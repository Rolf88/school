/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.maventest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RolfMoikjær
 */
public class Program {

    private static String name;

    public static void setName(String value) {
        name = value;
    }

    private static void sequenceThing() {
        int a = 8;
        setName("Kurt");
        {
            //This is a block
            int b = 9;
            System.out.println("Result: " + (a + b));
            // int a = 10; not legal
        }
        int b = 12;// this is legal
        ;;;;;;;;;//emty statements - beware
        {
        }
        {
        }
        {
        }
        {
        }
        {
        } // more empty statements
        class x {

            int y;
        }
    }

    private static void selectionThing() {
        int a = 8;
        if (a > 7) {
            System.out.println("A was: " + a);
        }

        if (a < 12) {
            a += 6;
            System.out.println("Now a is: " + a);
        }

        int choice = 5;
        if (choice == 1) {
            System.out.println("Monday");
        } else {
            System.out.println("Not Monday");
        }

        if (choice < 4) {
            if (choice > 2) {
                System.out.println("Wednesday");
            } else {
                System.out.println("Very early iin the week");
            }
        } else {
            System.out.println("Weekend approaching");
        }

//        if (choice == 1) {
//            System.out.println("Monday");
//        } else if (choice == 2) {
//            System.out.println("Tuesday");
//        } else if (choice == 3) {
//            System.out.println("Wedensday");
//        } else if (choice == 4) {
//            System.out.println("Thursday");
//        } else if (choice == 5) {
//            System.out.println("Friday");
//        } else {
//            System.out.println("Weekend");
//        }
// Use the switch case instead of the if else code above, it is faster with switchcase.
        switch (choice) {
            case 1:
                System.out.println("Mondayh");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wedensday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            default:
                System.out.println("Weekend");
                break;
        }

        try {
            Program program = new Program();
            Method method = program.getClass().getMethod("setNAme", String.class);
            method.invoke(program, "Yvonne");
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private static void iterationThing() throws IOException {
        for (int i = 0; i < 10; i++) {
            System.out.println("#" + i);
        }

        int[] as = new int[]{7, 9, 13, 17, 23};
        for (int a : as) {
            if (a == 13) {
                continue;
            }
            System.out.println("#" + a);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = in.readLine();
        while (line != null && !line.isEmpty()) {
            System.out.println(">>" + line + "<<");
            line = in.readLine();
        }

    }

    public static void main(String... args) throws IOException {
        sequenceThing();
        selectionThing();
        iterationThing();
    }
}
