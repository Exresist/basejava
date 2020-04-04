package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    private static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int storageSize = 0;

    protected abstract int getIndex(String uuid);

    public void delete(String uuid) {
        int i = getIndex(uuid);
        if (i > -1) {
            remove(i);
        } else {
            System.out.println("ERROR! Resume does not contain " + uuid + "!");
        }
    }

    protected abstract void remove(int i);


    public void save(Resume resume) {
        if (storageSize + 1 > storage.length) {
            System.out.println("ERROR! Resume contains maximum elements!");
        } else {
            int index = getIndex(resume.getUuid());
            if (index > -1) {
                System.out.println("ERROR! Resume already contain " + resume.getUuid() + "!");
            } else {
                add(resume, index);
            }
        }
    }

    protected abstract void add(Resume resume, int index);

    public void clear() {
        Arrays.fill(storage, 0, storageSize, null);
        storageSize = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
        } else {
            System.out.println("ERROR! Resume does not contain " + resume.getUuid() + "!");
        }
    }

    public int size() {
        return storageSize;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return storage[index];
        } else {
            System.out.println("ERROR! Resume does not contain " + uuid + "!");
        }
        return null;
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, storageSize);
    }
}
