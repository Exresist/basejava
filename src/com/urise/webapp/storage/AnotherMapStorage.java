package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnotherMapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected boolean existResume(Object searchKey) {
        return storage.containsValue(searchKey);
    }

    @Override
    protected void removeResume(Object searchKey) {
        Resume resume = (Resume) searchKey;
        storage.remove(resume.getUuid(), resume);
    }

    @Override
    protected void updateResume(Object searchKey, Resume resume) {
        Resume r = (Resume) searchKey;
        storage.replace(r.getUuid(), resume);
    }

    @Override
    protected void addResume(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected List<Resume> copyAllResume() {

        return new ArrayList<>(storage.values());
    }
}
