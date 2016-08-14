/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadprogrammingproducerconsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author RolfMoikjær
 */
public class ThreadProgrammingProducerConsumer {

    private static BlockingQueue S1;
    private static BlockingQueue S2;

    public static void main(String[] args) throws InterruptedException {
        S1 = new ArrayBlockingQueue(12);
        S2 = new ArrayBlockingQueue(12);

        S1.put(4);
        S1.put(5);
        S1.put(7);
        S1.put(12);
        S1.put(21);
        S1.put(22);
        S1.put(34);
        S1.put(35);
        S1.put(36);
        S1.put(37);
        S1.put(42);

        Producer p1 = new Producer(S1, S2);
        Producer p2 = new Producer(S1, S2);
        Producer p3 = new Producer(S1, S2);
        Producer p4 = new Producer(S1, S2);

        long start = System.nanoTime();

        p1.run();
        p2.run();
        p3.run();
        p4.run();

        try {
            p1.join();
            p2.join();
            p3.join();
            p4.join();

        } catch (Exception e) {
        }

        long end = System.nanoTime();
        System.out.println("Time Sequental: " + (end - start));
//        List finRes = new ArrayList();
//
//        S2.drainTo(finRes);
//
//        for (int i = 0; i < finRes.size(); i++) {
//            Object k = finRes.get(i);
//            long n = (long) k;
//            System.out.println("Number Produced: " + n);
//        }
    }
}
