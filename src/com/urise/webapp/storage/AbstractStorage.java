package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {




    public void update(Resume resume) {
        if (itemExist(resume.getUuid())) {
            updateResume(resume);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }


    public void save(Resume resume) {
        if (itemExist(resume.getUuid())) {
            throw new ExistStorageException(resume.getUuid());
        } else addResume(resume);

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
            removeResume(uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract boolean itemExist(String uuid);

    protected abstract Resume getResume(String uuid);

    protected abstract void removeResume(String uuid);

    protected abstract void updateResume(Resume resume);

    protected abstract void addResume(Resume resume);



}
