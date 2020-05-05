package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        if (isExist(resume)) {
            updateResume(resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public abstract Object getKey(String uuid);

    public void save(Resume resume) {
        if (isExist(resume)) {
            throw new ExistStorageException(resume.getUuid());
        } else addResume(resume);

    }

    public Resume get(String uuid) {
        if (isExist(new Resume(uuid))) {
            return getResume(uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract Resume getResume(String uuid);

    public void delete(String uuid) {
        if (isExist(new Resume(uuid))) {
            removeResume(uuid);
        }
        throw new NotExistStorageException(uuid);

    }

    protected abstract boolean isExist(Resume resume);

    protected abstract void removeResume(String uuid);

    protected abstract void updateResume(Resume resume);

    protected abstract void addResume(Resume resume);
}
