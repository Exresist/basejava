package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, storageSize, searchKey);
    }

    @Override
    protected void remove(int i) {
        for (int index = i + 1; index < storageSize; index++) {
            storage[index - 1] = storage[index];
        }
        storage[storageSize - 1] = null;
        storageSize--;
    }

    @Override
    protected void add(Resume resume, int index) {
        for (int i = storageSize; i > -index - 1; i--) {
            storage[i] = storage[i - 1];
        }
        storageSize++;
        storage[-index - 1] = resume;
    }
}
