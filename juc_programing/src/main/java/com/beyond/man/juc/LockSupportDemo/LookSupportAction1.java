package com.beyond.man.juc.LockSupportDemo;

import java.util.concurrent.locks.LockSupport;

/**
 * public static void park(Object blocker); // 暂停当前线程
 * public static void parkNanos(Object blocker, long nanos); // 暂停当前线程，不过有超时时间的限制
 * public static void parkUntil(Object blocker, long deadline); // 暂停当前线程，直到某个时间
 * public static void park(); // 无期限暂停当前线程
 * public static void parkNanos(long nanos); // 暂停当前线程，不过有超时时间的限制
 * public static void parkUntil(long deadline); // 暂停当前线程，直到某个时间
 * public static void unpark(Thread thread); // 恢复当前线程
 * public static Object getBlocker(Thread t);
 */
public class LookSupportAction1 {
    public static Object lock = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("in " + getName());
                LockSupport.park();
                /**
                 * park不需要获取某个对象的锁
                 * 因为中断的时候park不会抛出InterruptedException异常，所以需要在park之后自行判断中断状态，然后做额外的处理
                 */
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "被中断了");
                }
                System.out.println(Thread.currentThread().getName() + "继续执行");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(1000L);
        t2.start();
        Thread.sleep(3000L);
        /**
         * t1打断后继续执行
         */
        t1.interrupt();
        /**
         * t2使用unpark唤醒线程
         */
        LockSupport.unpark(t2);
        t1.join();
        t2.join();

    }
}

