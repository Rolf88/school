/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heepqueue;

import java.util.Comparator;
import simplyastar.Node;

/**
 *
 * @author RolfMoikjær
 */
public class TestHeap {

    public static void main(String[] args) {
        HeepQueue<MyNode> heap = new HeepQueue<MyNode>(Comparator.naturalOrder());

        MyNode a = new MyNode("A", 3);
        MyNode b = new MyNode("B", 4);
        MyNode c = new MyNode("C", 7);
        MyNode d = new MyNode("D", 34);
        MyNode e = new MyNode("E", 2);
        MyNode f = new MyNode("F", 9);
        MyNode g = new MyNode("G", 32);
        MyNode h = new MyNode("H", 12);

        heap.add(a);
        heap.add(b);
        heap.add(c);
        heap.add(d);
        heap.add(e);
        heap.add(f);
        heap.add(g);
        heap.add(h);

        h.setValue(0);
        heap.update(h);

        while (!heap.isEmpty()) {
            MyNode n = heap.poll();
            System.out.println(n);
        }

        System.out.println("\nDone");
    }
}
