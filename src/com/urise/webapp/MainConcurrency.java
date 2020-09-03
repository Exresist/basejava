package com.urise.webapp;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10_000;
    private static volatile int counter;

    private static final Object object1 = new Object();
    private static final Object object2 = new Object();

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");


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
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newCachedThreadPool();
//        CompletionService completionService = new ExecutorCompletionService(executorService);

//        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            executorService.submit(() ->
//            Thread thread = new Thread(() ->
            {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
                latch.countDown();
            });
//            thread.start();
//            threads.add(thread);
        }
       /* threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });*/
        try {
            latch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println(counter);
        System.out.println(thread0.getState());
        deadLock(object1, object2);
        deadLock(object2, object1);

    }

    private static void deadLock(Object lock1, Object lock2) {
        new Thread(() -> {
            System.out.println("Waiting lock 1");
            synchronized (lock1) {
                System.out.println("Holding lock 1");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Waiting lock 2");
                synchronized (lock2) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Holding lock 2");
                }
            }
        }).start();
    }

    private synchronized void inc() {
        counter++;
    }


}


