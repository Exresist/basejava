package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    // Проверка на существование резюме с заданным uuid
    @Override
    protected boolean itemExist(Resume resume) {
        return storage.contains(resume);
    }

    // Получение резюме с заданным uuid
    @Override
    protected Resume getResume(Resume resume) {
        return storage.get(storage.indexOf(resume));

    }

    // Удаление резюме с заданным uuid
    @Override
    protected void removeResume(Resume resume) {
        storage.remove(resume);
    }

    // обновление резюме с заданным uuid
    @Override
    protected void updateResume(Resume resume) {
        storage.set(storage.indexOf(resume), resume);
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
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
