package com.beyond.man.juc.ThreadLocalDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * ThreadLocal<T>其实是与线程绑定的一个变量。ThreadLocal和Synchonized都用于解决多线程并发访问。
 * 但是ThreadLocal与synchronized有本质的区别。Synchronized用于线程间的数据共享，而ThreadLocal
 * 则用于线程间的数据隔离。Synchronized是利用锁的机制，使变量或代码块在某一时该只能被一个线程访问。
 * 而ThreadLocal为每一个线程都提供了变量的副本，使得每个线程在某一时间访问到的并不是同一个对象，
 * 这样就隔离了多个线程对数据的数据共享。而Synchronized却正好相反，它用于在多个线程间通信时能够获得数据共享。
 */
public class ThreadLocalAction1 {
    /**
     * 我们可以看到，通过这段代码实例化了一个ThreadLocal对象。我们只需要实例化对象一次，并且也不需要知道
     * 它是被哪个线程实例化。虽然所有的线程都能访问到这个ThreadLocal实例，但是每个线程却只能访问到自己
     * 通过调用ThreadLocal的set()方法设置的值。即使是两个不同的线程在同一个ThreadLocal对象上设置了不同的值
     * ，他们仍然无法访问到对方的值。
     */
    private static ThreadLocal<String> TL1 = new ThreadLocal<>();

    public static class MyCallabel implements Callable {
        @Override
        public String call() throws Exception {
            Thread thread = Thread.currentThread();
            //The ThreadLocal objects act as keys ,that is TL1
            TL1.set(thread.getName());
            return TL1.get();
        }
    }

    public static class MyCallabe2 implements Callable {
        @Override
        public String call() throws Exception {
            Thread thread = Thread.currentThread();
            TL1.set(thread.getId() + "");
            return TL1.get();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallabel myCallabel1 = new MyCallabel();
        MyCallabe2 myCallabel2 = new MyCallabe2();
        FutureTask ft1 = new FutureTask<String>(myCallabel1);
        FutureTask ft2 = new FutureTask<String>(myCallabel2);
        new Thread(ft1).start();
        new Thread(ft2).start();

        String s1 = (String) ft1.get();
        System.out.println("s1 = " + s1);
        String s2 = (String) ft2.get();
        System.out.println("s2 = " + s2);

    }
}
