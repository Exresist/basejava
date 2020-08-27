package com.urise.webapp.util;

public class LazySingleton {
    volatile private static LazySingleton INSTANCE;

    private static class LazySingletonHolder {
        private static final LazySingleton INSTANCE = new LazySingleton();
    }

    public static LazySingleton getInstance() {
        return LazySingletonHolder.INSTANCE;
        //        if (INSTANCE == null) {
//            INSTANCE = new LazySingleton();
//        }
//        return INSTANCE;

    }

    private LazySingleton() {

    }
}
