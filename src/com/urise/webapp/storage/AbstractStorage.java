package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        if (itemExist(resume)) {
            updateResume(resume);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    public abstract Object getKey(Resume resume);

    public void save(Resume resume) {
        if (itemExist(resume)) {
            throw new ExistStorageException(resume.getUuid());
        } else addResume(resume);

    }

    public Resume get(String uuid) {
        Resume resume = new Resume(uuid);
        if (itemExist(resume)) {
            return getResume(resume);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract Resume getResume(Resume resume);

    public void delete(String uuid) {
        Resume resume = new Resume(uuid);
        if (itemExist(resume)) {
            removeResume(resume);
        }
        throw new NotExistStorageException(uuid);

    }

    protected abstract boolean itemExist(Resume resume);

    protected abstract void removeResume(Resume resume);

    protected abstract void updateResume(Resume resume);

    protected abstract void addResume(Resume resume);
}
