package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage  {

    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int storageSize = 0;

    protected abstract int getIndex(String uuid);

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            remove(index);
            storage[storageSize - 1] = null;
            storageSize--;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract void remove(int index);


    public void save(Resume resume) {
        if (storageSize >= storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            int index = getIndex(resume.getUuid());
            if (index > -1) {
                throw new ExistStorageException(resume.getUuid());
            } else {
                add(resume, index);
                storageSize++;
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
            throw new NotExistStorageException(resume.getUuid());
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
            throw new NotExistStorageException(uuid);
        }
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, storageSize);
    }
}
