package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    private ArrayList<Resume> storage = new ArrayList<>();

    // Проверка на существование резюме с заданным uuid
    @Override
    protected boolean itemExist(String uuid) {
        Resume resume = new Resume(uuid);
        return storage.contains(resume);
    }

    // Получение резюме с заданным uuid
    @Override
    protected Resume getResume(String uuid) {
        Resume resume = new Resume(uuid);
        return storage.get(storage.indexOf(resume));

    }

    // Удаление резюме с заданным uuid
    @Override
    protected void removeResume(String uuid) {
        Resume resume = new Resume(uuid);
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
