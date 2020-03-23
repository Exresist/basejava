

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int storageSize = 0;

    void clear() {
        for (int i = 0; i < storageSize; i++) {
            storage[i] = null;
        }
        storageSize = 0;
    }

    void save(Resume r) {
        storage[storageSize] = r;
        storageSize++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int deletedIndex = 0;
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].uuid.equals(uuid)) {
                deletedIndex = i;
                storageSize--;
                break;
            }
        }
            for (int j = deletedIndex; j < storageSize; j++) {
                storage[j] = storage[j + 1];
            }

    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resume = new Resume[size()];
        for (int i = 0; i < size(); i++) {
            resume[i] = storage[i];
        }
        return resume;
    }

    int size() {
       return storageSize;
    }
}
