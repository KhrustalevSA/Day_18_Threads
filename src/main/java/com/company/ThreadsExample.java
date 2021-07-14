package com.company;

import java.util.concurrent.*;


class SimpleThread extends Thread {
    public String name;
    private long count = 0;
    private long maxCount = 30000;

    public void setMaxCount(long maxCount) {
        this.maxCount = maxCount;
    }


    public SimpleThread(String name) {
        this.name = name;
    }

    public void run() {

        while (count < maxCount) {
            ++count;
            System.out.println(name + " копает " + count);
            Thread.yield();
        }
    }
}

class SimpleThread2 extends Thread {
    public String name;
    private long count = 0;
    private long maxCount = 30000;

    public void setMaxCount(long maxCount) {
        this.maxCount = maxCount;
    }


    public SimpleThread2(String name) {
        this.name = name;
    }

    public void run() {
        try {

            while (count < maxCount) {
                ++count;
                System.out.println(name + " копает " + count);
                Thread.yield();
            }
        }
        finally {
            System.out.println(name + " устал");
        }
    }
}

class SimpleThread3 extends Thread {
    public void run() {
        throw new RuntimeException("RuntimeException");
    }
}

class Worker1 implements Runnable {
    private String name;
    private long count = 0;
    private long maxCount = 30000;

    public Worker1(String name) {
        this.name = name;
    }

    public void run() {
        while (count < maxCount) {
            ++count;
            System.out.println(name + " копает " + count);
            Thread.yield();
        }

    }
}

class Worker2 implements Callable<String> {
    private String name;
    private long count = 0;
    private long maxCount = 10000;

    public Worker2(String name) {
        this.name = name;
        count = 0;
    }

    public String call() {
        while (count < maxCount) {
            ++count;
            System.out.println(name + " копает " + count);
            Thread.yield();
        }
        return name + ": Всё! Перекур!";
    }
}

class ThreadHelper extends Thread {
    private String name;
    private long count;
    SimpleThread other;

    public ThreadHelper(String name, SimpleThread other) {
        this.other = other;
        this.name = name;
        count = 0;
    }

    public void run() {
        try {
            while (count < 20000) {
                ++count;
                System.out.println(name + " копает " + count);
                Thread.yield();
            }
            System.out.println(name + " ждёт " + other.name);
            other.join();
            System.out.println(name + " дождался " + other.name);
            while (count < 30000) {
                ++count;
                System.out.println(name + " копает " + count);
                Thread.yield();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


public class ThreadsExample {

    public static void main(String[] args) throws Exception {
        //runThread();
        //runRunnable();
        //runExecutor();
        //submitExecutor();
        join();
        //runThreadWithPriority();
        //runThreadWithDaemon();
        //runThreadWithException();
    }

    private static void runThread() {
        Thread w1 = new SimpleThread("Иван");
        Thread w2 = new SimpleThread("Василий");
        Thread w3 = new SimpleThread("Пётр");
        w1.start();
        w2.start();
        w3.start();
    }

    private static void runRunnable() {
        Thread w1 = new Thread(new Worker1("Иван"));
        Thread w2 = new Thread(new Worker1("Василий"));
        Thread w3 = new Thread(new Worker1("Пётр"));
        w1.start();
        w2.start();
        w3.start();
    }

    private static void runExecutor() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Worker1("Иван"));
        executorService.execute(new Worker1("Василий"));
        executorService.execute(new Worker1("Пётр"));
    }

    private static void submitExecutor() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> f1 = executorService.submit(new Worker2("Иван"));
        Future<String> f2 = executorService.submit(new Worker2("Василий"));
        Future<String> f3 = executorService.submit(new Worker2("Пётр"));

        System.out.println(f1.get());
        System.out.println(f2.get());
        System.out.println(f3.get());
    }

    private static void join() {
        SimpleThread w1 = new SimpleThread("Иван");
        Thread w2 = new ThreadHelper("Василий", w1);
        w1.start();
        w2.start();
    }

    private static void runThreadWithPriority() {
        Thread w1 = new SimpleThread("Иван");
        Thread w2 = new SimpleThread("Василий");
        Thread w3 = new SimpleThread("Пётр");
        w1.setPriority(Thread.NORM_PRIORITY);
        w2.setPriority(Thread.MIN_PRIORITY);
        w3.setPriority(Thread.MAX_PRIORITY);
        w1.start();
        w2.start();
        w3.start();
    }

    private static void runThreadWithDaemon() {
        SimpleThread2 w1 = new SimpleThread2("Иван");
        SimpleThread2 w2 = new SimpleThread2("Василий");
        SimpleThread2 w3 = new SimpleThread2("Пётр");
        w1.setMaxCount(50000);
        w1.setDaemon(true);
        w2.setDaemon(true);
        w3.setDaemon(true);
        w1.start();
        w2.start();
        w3.start();
    }
    private static void runThreadWithException() {
        SimpleThread3 w1 = new SimpleThread3();
        w1.start();
    }
}
