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

    @Override
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
}
