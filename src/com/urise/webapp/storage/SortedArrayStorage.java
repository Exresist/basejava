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
    public void delete(String uuid) {
        int i = getIndex(uuid);
        if (i > -1) {
            for (int index = i + 1; index < storageSize; index++) {
                storage[index - 1] = storage[index];
            }
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
            if (index > -1) {
                System.out.println("ERROR! Resume already contain " + resume.getUuid() + "!");
            } else {
                for (int i = storageSize; i > -index - 1; i--) {
                    storage[i] = storage[i - 1];
                }
                storageSize++;
                storage[-index - 1] = resume;
            }
        }

    }
}
