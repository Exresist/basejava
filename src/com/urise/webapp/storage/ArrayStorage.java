package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int storageSize = 0;

    public void clear() {
        Arrays.fill(storage, 0, storageSize, null);
        storageSize = 0;
    }

    public void update(Resume r) {
        boolean Exist = false;
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                storage[i] = r;
                Exist = true;
                break;
            }
        }
        if (!Exist) {
            System.out.println("ERROR! Resume does not contain this uuid!");
        }
    }

    public void save(Resume r) {
        boolean Exist = false;
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                Exist = true;
                break;
            }
        }
        if (Exist) {
            System.out.println("ERROR! Resume already contain this uuid!");
        } else {
            storage[storageSize] = r;
            storageSize++;
        }

    }

    public Resume get(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        boolean Exist = false;
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = storage[storageSize - 1];
                storage[storageSize - 1] = null;
                storageSize--;
                Exist = true;
                break;
            }
        }
        if (!Exist) {
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
