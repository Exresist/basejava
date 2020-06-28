package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnotherMapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public String getKey(String fullName) {
        return fullName;
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
        storage.replace((String) key, resume);
    }

    @Override
    protected void addResume(Resume resume, Object key) {
        storage.put((String) key, resume);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
