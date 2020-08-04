package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "name");
        return Arrays.binarySearch(storage, 0, storageSize, searchKey, RESUME_COMPARATOR);
    }

    @Override
    protected void remove(int index) {
        System.arraycopy(storage, index + 1, storage, index, storageSize - index + 1);
    }

    @Override
    protected void add(Resume resume, int index) {
        int insertIndex = -index - 1;
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, storageSize - insertIndex);
        storage[insertIndex] = resume;
    }
}
