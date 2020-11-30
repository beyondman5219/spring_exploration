package com.beyond.man.juc.LockSupportDemo;

import java.util.concurrent.locks.LockSupport;

/**
 *  LockSupport 是个工具类，它的主要作用是挂起和唤醒线程，
 * 该工具类是创建锁和其他同步类的基础。
 * LockSupport 类与每 个 使用它的线程都会关联一 个许可证，在 默认情况下调用
 * LockSupport 类的方法的线程是不持有许可证的。 LockSupport 是使用 Unsafe 类实现的，
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
         * 使得给定线程t1许可可用，如果线程t1在park上阻塞，则结束阻塞状态
         * 否则，保证当线程t1执行unpark后，当再次执行park时候，许可可用，不会阻塞立即返回
         */
        LockSupport.unpark(t1);
        System.out.println("unpark invoked");
    }

}