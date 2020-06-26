package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ListStorage;

import java.util.*;

public class MainCollections {
    public static void main(String[] args) {
        final String UUID_1 = "uuid1";
        final Resume RESUME_1 = new Resume(UUID_1);
        final String UUID_2 = "uuid2";
        final Resume RESUME_2 = new Resume(UUID_2);
        final String UUID_3 = "uuid3";
        final Resume RESUME_3 = new Resume(UUID_3);
        ListStorage collection = new ListStorage();
        collection.save(RESUME_1);
        collection.save(RESUME_2);
        collection.save(RESUME_3);
        Resume[] all = collection.getAll();
        System.out.println("----------------------------");
        if (all.length == 0) {
            System.out.println("Empty");
        } else {
            for (Resume r : all) {
                System.out.println(r);
            }
        }
        System.out.println("----------------------------");
        System.out.println(collection.get(UUID_1));
        collection.delete(UUID_2);
        System.out.println(collection.size());
        collection.update(RESUME_1);
        collection.clear();
        all = collection.getAll();
        System.out.println("----------------------------");
        if (all.length == 0) {
            System.out.println("Empty");
        } else {
            for (Resume r : all) {
                System.out.println(r);
            }
        }
        System.out.println("----------------------------");
        Map<String, Resume> map = new HashMap<>();
        map.put(UUID_1, RESUME_1);
        map.put(UUID_2, RESUME_2);
        map.put(UUID_3, RESUME_3);
        List<Resume> resumes = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        resumes.remove(1);
    }
}
