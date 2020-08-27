package com.urise.webapp;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10_000;
    private static volatile int counter;
    private static int num;

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "," + Thread.currentThread().getState());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + "," + getState().toString());

            }
        };
        thread0.start();
        new Thread(() -> System.out.println(Thread.currentThread().getName() + "," + Thread.currentThread().getState())).start();
        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }
        threads.forEach(t -> {
            try {
                t.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println(counter);
        System.out.println(thread0.getState());


        Thread thread1 = new Thread(mainConcurrency::add1);

        Thread thread2 = new Thread(mainConcurrency::add2);
        thread1.start();
        thread2.start();

        System.out.println(num);
    }

    private synchronized void inc() {
        counter++;
    }

    private synchronized void add1() {
        num++;
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        add2();
    }

    private synchronized void add2() {
        if (num < 10){
            num++;
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            add1();
        }
    }
}
