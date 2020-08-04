package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private final List<Resume> storage = new ArrayList<>();

    // Проверка на существование резюме с заданным uuid
    @Override
    protected boolean isExistResume(Integer key) {
        return key != null;
    }

    @Override
    public Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    // Получение резюме с заданным uuid
    @Override
    protected Resume getResume(Integer key) {
        return storage.get(key);
    }

    // Удаление резюме с заданным uuid
    @Override
    protected void removeResume(Integer key) {
        int index = key;
        storage.remove(index);
    }

    // обновление резюме с заданным uuid
    @Override
    protected void updateResume(Integer key, Resume resume) {
        storage.set(key, resume);
    }

    @Override
    protected void addResume(Resume resume, Integer key) {
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

    @Override
    public List<Resume> copyAllResume() {
        return new ArrayList<>(storage.size());
    }
}
