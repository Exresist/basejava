package com.urise.webapp.storage;

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
}
