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
        System.arraycopy(storage, i + 1, storage, i + 1 - 1, storageSize - i + 1);
        storage[storageSize - 1] = null;
    }

    @Override
    protected void add(Resume resume, int index) {
        for (int i = storageSize; i > -index - 1; i--) {
            storage[i] = storage[i - 1];
        }
        storage[-index - 1] = resume;
    }
}
