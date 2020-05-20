package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public String getKey(String uuid) {
        return storage.get(uuid).getUuid();
    }

    @Override
    protected Resume getResume(Object key) {
        return storage.get(key);
    }

    @Override
    protected boolean existResume(Object key) {
        return storage.containsKey(key);
    }

    @Override
    protected void removeResume(Object key) {
        storage.remove(key);
    }

    @Override
    protected void updateResume(Object key, Resume resume) {
        storage.replace((String) key,resume);
    }

    @Override
    protected void addResume(Resume resume, Object key) { storage.put((String) key, resume); }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return  storage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
