package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    private static final Comparator<Resume> RESUME_COMPARATOR = SortedArrayStorage::compare;

    protected static int compare(Resume o1, Resume o2) {
        return o1.getFullName().equals(o2.getFullName())
                ? o1.getUuid().compareTo(o2.getUuid())
                : o1.getFullName().compareTo(o2.getFullName());

    }

    public void update(Resume resume) {
        Object searchKey = getExistedSearchKey(resume.getUuid());
        updateResume(searchKey, resume);


    }

    public void save(Resume resume) {
        Object searchKey = getNotExistedSearchKey(resume.getUuid());
        addResume(resume, searchKey);

    }

    public Resume get(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        return getResume(searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        removeResume(searchKey);
    }

    private Object getExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!existResume(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (existResume(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = copyAllResume();
        list.sort(RESUME_COMPARATOR);
        return list;
    }
    protected abstract Resume getResume(Object key);

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean existResume(Object key);

    protected abstract void removeResume(Object key);

    protected abstract void updateResume(Object key, Resume resume);

    protected abstract void addResume(Resume resume, Object key);

    protected abstract List<Resume> copyAllResume();
}
