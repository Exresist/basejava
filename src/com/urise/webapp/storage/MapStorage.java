package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected boolean existResume(Object searchKey) {
        return storage.containsKey(searchKey);
    }

    @Override
    protected void removeResume(Object searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected void updateResume(Object searchKey, Resume resume) {
        storage.replace((String) searchKey,resume);
    }

    @Override
    protected void addResume(Resume resume, Object searchKey) { storage.put((String) searchKey, resume); }

    @Override
    protected List<Resume> copyAllResume() {

        return new ArrayList<>(storage.values());
    }

    @Override
    public void clear() {
        storage.clear();
    }


    @Override
    public int size() {
        return storage.size();
    }
}
