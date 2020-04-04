package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {


    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void remove(int i) {
        storage[i] = storage[storageSize - 1];
        storage[storageSize - 1] = null;
        storageSize--;
    }

    @Override
    protected void add(Resume resume, int index) {
        storage[storageSize] = resume;
        storageSize++;
    }

}
