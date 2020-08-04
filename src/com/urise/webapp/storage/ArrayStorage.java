package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int index = 0; index < storageSize; index++) {
            if (storage[index].getUuid().equals(uuid)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    protected void remove(int i) {
        storage[i] = storage[storageSize - 1];
    }

    @Override
    protected void add(Resume resume, int index) {
        storage[storageSize] = resume;
    }
}
