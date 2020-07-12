package com.urise.webapp;

import com.urise.webapp.model.SectionType;

public class TestSingleton {
    private static TestSingleton instance = new TestSingleton();

    private static TestSingleton getInstance() {
        if (instance == null){
            instance = new TestSingleton();
        }
        return instance;
    }
    private TestSingleton(){

    }
    private static void Main(String[] args){
        TestSingleton.getInstance().toString();
        Singleton instance = Singleton.valueOf("INSTANCE");
        System.out.println(instance.ordinal());

        for (SectionType type : SectionType.values()) {
            System.out.println(type.getTitle());
        }

    }
    public enum Singleton {
        INSTANCE
    }
}
