package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    public ListStorage() {
        super(new ArrayList<>());
    }

    // Проверка на существование резюме с заданным uuid
    @Override
    protected boolean itemExist(String uuid) {
        Resume resume = new Resume(uuid);
        return collectionStorage.contains(resume);
    }

    // Получение резюме с заданным uuid
    @Override
    protected Resume getResume(String uuid) {
        Resume resume = new Resume(uuid);
        ArrayList<Resume> arrayList = (ArrayList<Resume>) collectionStorage;
        return arrayList.get(arrayList.indexOf(resume));

    }

    // Удаление резюме с заданным uuid
    @Override
    protected void removeItem(String uuid) {
        Resume resume = new Resume(uuid);
        collectionStorage.remove(resume);
    }

    // обновление резюме с заданным uuid
    @Override
    protected void updateItem(Resume resume) {
        ArrayList<Resume> arrayList = (ArrayList<Resume>) collectionStorage;
        arrayList.set(arrayList.indexOf(resume), resume);
    }

    @Override
    public Resume[] getAll() {
        return collectionStorage.toArray(new Resume[0]);
    }
}
