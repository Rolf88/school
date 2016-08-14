/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadprogrammingproducerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RolfMoikjær
 */
public class Producer extends Thread {

    private static BlockingQueue S1;
    private static BlockingQueue S2;

    public Producer(BlockingQueue S1, BlockingQueue S2) {
        this.S1 = S1;
        this.S2 = S2;
    }

    private long fib(long n) {
        if ((n == 0) || (n == 1)) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    private synchronized void produce() {
        Object k;
        while ((k = S1.poll()) != null) {
            long p = fib((int) k);
            try {
                S2.put(p);

            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadProgrammingProducerConsumer.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Number produced : " + p);
        }

    }

    public void run() {
        produce();
    }

}
