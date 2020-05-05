package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    // Проверка на существование резюме с заданным uuid
    @Override
    protected boolean isExist(Resume resume) {
        return storage.contains(resume);
    }

    @Override
    public Integer getKey(String uuid) {
        for (Resume resume : storage
        ) {
            if (resume.getUuid().equals(uuid)) {
                return storage.indexOf(resume);
            }
        }
        return null;
    }

    // Получение резюме с заданным uuid
    @Override
    protected Resume getResume(String uuid) {
        return storage.get(getKey(uuid));

    }

    // Удаление резюме с заданным uuid
    @Override
    protected void removeResume(String uuid) {
        storage.removeIf(resume -> resume.getUuid().equals(uuid));
    }

    // обновление резюме с заданным uuid
    @Override
    protected void updateResume(Resume resume) {
        storage.set(getKey(resume.getUuid()), resume);
    }

    @Override
    protected void addResume(Resume resume) {
        storage.add(resume);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return (Resume[]) storage.toArray();
    }

    @Override
    public int size() {
        return storage.size();
    }
}
