package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

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
    protected void addResume(Resume resume, Object searchKey) {
        if (storageSize == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            add(resume, (Integer) searchKey);
            storageSize++;
        }
    }

    @Override
    protected void updateResume(Object searchKey, Resume resume) {
        storage[(Integer) searchKey] = resume;
    }

    @Override
    protected void removeResume(Object searchKey) {
        remove((Integer) searchKey);
        storage[storageSize - 1] = null;
        storageSize--;
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return storage[(Integer) searchKey];
    }

    @Override
    protected boolean existResume(Object searchKey) {
        return (Integer) searchKey > -1;
    }

    @Override
    public List<Resume> copyAllResume() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, storageSize));
    }

    protected abstract void add(Resume resume, int index);

    protected abstract void remove(int index);

    protected abstract Integer getSearchKey(String uuid);
}
