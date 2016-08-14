/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsforexamen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author RolfMoikjær
 */
public class TestsForExamen {

    public static void main(String[] args) {

//        Shop<?> b = new Shop();
//
//        Shop<Sedan> s = new Shop();
//
//        Vehicle v = (Vehicle) b.buyFrom();
//        s.sellTo(s.buyFrom());
        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8};
        List<Integer> listOfIntegers
                = new ArrayList<>(Arrays.asList(intArray));

        System.out.println("listOfIntegers:");
        listOfIntegers
                .parallelStream()
                .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println("");

    }

}

class F<T> {

    public void M() {
        T[] tarr; // Legal declaration
        G<T>[] ctarr; // Legal declaration
        G<Integer>[] ciarr; // Legal declaration
// tarr = new T[5]; // Illegal generic array creation
// ctarr = new G<T>[5]; // Illegal generic array creation
// ciarr = new G<Integer>[5]; // Illegal generic array creation
        ArrayList<T> tlist; // Legal declaration
        ArrayList<G<T>> ctlist; // Legal declaration
        ArrayList<G<Integer>> cilist; // Legal declaration
        tlist = new ArrayList<T>(); // Legal array list creation
        ctlist = new ArrayList<G<T>>(); // Legal array list creation
        cilist = new ArrayList<G<Integer>>(); // Legal array list creation
    }
}

class Vehicle {

    int x;
    int y;

    public Vehicle(int x, int y) {
        this.x = x;
        this.y = y;
    }

}

class Car extends Vehicle {

    public Car(int x, int y) {
        super(x, y);
    }
}

class Sedan extends Car {

    public Sedan(int x, int y) {
        super(x, y);
    }
}

class Shop<T> {

    private T thing;

    public T buyFrom() {
        return thing;
    }

    public void sellTo(T thing) {
        this.thing = thing;
    }

}

class SPoint {

    static ArrayList<SPoint> allpoints = new ArrayList<SPoint>();
    int x, y;

    SPoint(int x, int y) {
        allpoints.add(this);
        this.x = x;
        this.y = y;
    }

    void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    int getIndex() {
        return allpoints.indexOf(this);
    }

    static int getSize() {
        return allpoints.size();
    }

    static SPoint getPoint(int i) {
        return allpoints.get(i);
    }
}
