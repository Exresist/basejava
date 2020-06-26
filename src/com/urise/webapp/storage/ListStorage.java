package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    // Проверка на существование резюме с заданным uuid
    @Override
    protected boolean existResume(Object key) {
        return key != null;
    }

    @Override
    public Integer getKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    // Получение резюме с заданным uuid
    @Override
    protected Resume getResume(Object key) {
        return storage.get((Integer) key);

    }

    // Удаление резюме с заданным uuid
    @Override
    protected void removeResume(Object key) {
        int index = (Integer) key;
        storage.remove(index);
    }

    // обновление резюме с заданным uuid
    @Override
    protected void updateResume(Object key, Resume resume) {
        storage.set((Integer) key, resume);
    }

    @Override
    protected void addResume(Resume resume, Object key) {
        storage.add(resume);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return storage;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
