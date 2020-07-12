package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage<String> {

    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected Resume getResume(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExistResume(String uuid) {
        return storage.containsKey(uuid);
    }

    @Override
    protected void removeResume(String uuid) {
        storage.remove(uuid);
    }

    @Override
    protected void updateResume(String uuid, Resume resume) {
        storage.replace(uuid, resume);
    }

    @Override
    protected void addResume(Resume resume, String uuid) {
        storage.put(uuid, resume);
    }

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
