package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int storageSize = 0;

    private int index(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        Arrays.fill(storage, 0, storageSize, null);
        storageSize = 0;
    }

    public void update(Resume resume) {
        int i = index(resume.getUuid());
        if (i == -1) {
            System.out.println("ERROR! Resume does not contain this uuid!");
        } else {
            storage[i] = resume;
        }
    }

    public void save(Resume resume) {
        if (storageSize + 1 > storage.length) {
            System.out.println("ERROR! Resume contains maximum elements!");
        } else {
            int i = index(resume.getUuid());
            if (i != -1) {
                System.out.println("ERROR! Resume already contain this uuid!");
            } else {
                storage[storageSize] = resume;
                storageSize++;
            }
        }

    }

    public Resume get(String uuid) {
        int i = index(uuid);
        if (i != -1) {
            return storage[i];
        }
        return null;
    }

    public void delete(String uuid) {
        int i = index(uuid);
        if (i != -1) {
            storage[i] = storage[storageSize - 1];
            storage[storageSize - 1] = null;
            storageSize--;
        } else {
            System.out.println("ERROR! Resume does not contain this uuid!");
        }

    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, storageSize);
        /*for (int i = 0; i < storageSize; i++) {
            resume[i] = storage[i];
        } */
    }

    public int size() {
        return storageSize;
    }

}
