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
public class LookSupportAction2 {
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");

    public static class ChangeObjectThread extends Thread {

        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /**
                 * 阻塞当前线程。直到unpark此线程。获得执行许可
                 */
                LockSupport.park();
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("被中断了");
                }
                System.out.println("继续执行");
            }
        }
    }

    public static void main(String[] args) {
        t1.start();
        /**
         * 使得给定线程t1许可可用，如果线程t1在park上阻塞，则接触阻塞状态
         * 否则，保证当线程t1执行unpark后，当再次执行park时候，许可可用，不会阻塞立即返回
         */
        LockSupport.unpark(t1);
        System.out.println("unpark invoked");
    }

}