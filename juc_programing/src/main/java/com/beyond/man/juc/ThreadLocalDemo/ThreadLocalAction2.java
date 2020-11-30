package com.beyond.man.juc.ThreadLocalDemo;

import java.util.concurrent.ExecutionException;

/**
 * InheritableThreadLocal类是ThreadLocal类的子类。ThreadLocal中每个线程拥有它自己的值，
 * 与ThreadLocal不同的是，InheritableThreadLocal允许一个线程以及该线程创建的所有子线程
 * 都可以访问它保存的值。
 */
public class ThreadLocalAction2 {

    private static ThreadLocal<Integer> TL1 = new ThreadLocal<>();

    public static class MyRunable implements Runnable {

        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            //The ThreadLocal objects act as keys ,that is TL1
            TL1.set((int) (Math.random() * 100D));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println(TL1.get());
            }
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread(new MyRunable()).start();
        new Thread(new MyRunable()).start();

    }
}
