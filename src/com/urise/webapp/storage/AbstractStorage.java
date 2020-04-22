package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collection;

public abstract class AbstractStorage implements Storage {
    protected Collection<Resume> collectionStorage;

    protected AbstractStorage(Collection<Resume> collectionStorage) {
        this.collectionStorage = collectionStorage;
    }

    public void clear() {
        collectionStorage.clear();
    }


    public void update(Resume resume) {
        if (itemExist(resume.getUuid())) {
            updateItem(resume);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }


    public void save(Resume resume) {
        if (itemExist(resume.getUuid())) {
            throw new ExistStorageException(resume.getUuid());
        } else collectionStorage.add(resume);

    }


    public Resume get(String uuid) {
        if (itemExist(uuid)) {
            return getResume(uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }


    public void delete(String uuid) {
        if (itemExist(uuid)) {
            removeItem(uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract boolean itemExist(String uuid);

    protected abstract Resume getResume(String uuid);

    protected abstract void removeItem(String uuid);

    protected abstract void updateItem(Resume resume);

    public abstract Resume[] getAll();


    public int size() {
        return collectionStorage.size();
    }
}
