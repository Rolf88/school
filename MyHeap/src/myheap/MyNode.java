/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myheap;

/**
 *
 * @author RolfMoikjær
 */
public class MyNode implements Comparable<MyNode> {

    private final String name;
    private int value;

    public MyNode(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return name + ": " + value;
    }

    @Override
    public int compareTo(MyNode o) {
        return this.value - o.value;
    }
}
