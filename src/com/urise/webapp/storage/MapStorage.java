package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public String getKey(String uuid) {
        return uuid;
    }

    @Override
    protected Resume getResume(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Resume resume) {
        return storage.containsKey(resume.getUuid());
    }

    @Override
    protected void removeResume(String uuid) {
        storage.remove(uuid);
    }

    @Override
    protected void updateResume(Resume resume) {
        storage.replace(resume.getUuid(),resume);
    }

    @Override
    protected void addResume(Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return (Resume[]) storage.values().toArray();
    }

    @Override
    public int size() {
        return storage.size();
    }
}
