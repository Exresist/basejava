package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;

public abstract class AbstractStorage implements Storage {

    protected static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) ->   o1.getFullName().compareTo(o2.getFullName());

    public void update(Resume resume) {
        Object key = getKey(resume.getUuid());
        if (existResume(key)) {
            updateResume(key, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }


    }

    public void save(Resume resume) {
        Object key = getKey(resume.getUuid());
        if (!existResume(key)) {
            addResume(resume, key);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }

    }

    public Resume get(String uuid) {
        Object key = getKey(uuid);
        if (existResume(key)) {
            return getResume(key);
        }
        throw new NotExistStorageException(uuid);


    }

    public void delete(String uuid) {
        Object key = getKey(uuid);
        if (existResume(key)) {
            removeResume(key);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract Resume getResume(Object key);

    protected abstract Object getKey(String uuid);

    protected abstract boolean existResume(Object key);

    protected abstract void removeResume(Object key);

    protected abstract void updateResume(Object key, Resume r);

    protected abstract void addResume(Resume resume, Object key);
}
