package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int storageSize = 0;

    public void clear() {
        Arrays.fill(storage, 0, storageSize, null);
        storageSize = 0;
    }

    public int size() {
        return storageSize;
    }

    @Override
    protected void addResume(Resume resume, Object key){
        if (storageSize == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            add(resume, (Integer) key);
            storageSize++;
        }
    }

    @Override
    protected void updateResume(Object key, Resume resume){
        storage[(Integer) key] = resume;
    }

    @Override
    protected void removeResume(Object key){
        remove((Integer) key);
        storage[storageSize - 1] = null;
        storageSize--;
    }

    @Override
    protected Resume getResume (Object key){
        return storage[(Integer) key];
    }

    @Override
    protected boolean existResume(Object key) {
        return (Integer) key >-1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, storageSize);
    }

    protected abstract void add(Resume resume, int index);

    protected abstract void remove(int index);

    protected abstract Integer getKey(String uuid);
}
