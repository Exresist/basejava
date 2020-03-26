package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int storageSize = 0;


    public void clear() {
        Arrays.fill(storage, 0, storageSize, null);
        storageSize = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("ERROR! Resume does not contain " + resume.getUuid() + "!");
        } else {
            storage[index] = resume;
        }
    }

    public void save(Resume resume) {
        if (storageSize + 1 > storage.length) {
            System.out.println("ERROR! Resume contains maximum elements!");
        } else {
            int index = getIndex(resume.getUuid());
            if (index != -1) {
                System.out.println("ERROR! Resume already contain " + resume.getUuid() + "!");
            } else {
                storage[storageSize] = resume;
                storageSize++;
            }
        }

    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        return null;
    }

    public void delete(String uuid) {
        int i = getIndex(uuid);
        if (i != -1) {
            storage[i] = storage[storageSize - 1];
            storage[storageSize - 1] = null;
            storageSize--;
        } else {
            System.out.println("ERROR! Resume does not contain " + uuid + "!");
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

    private int getIndex(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
