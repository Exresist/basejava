import sun.security.util.ArrayUtil;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < size(); i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid))
                return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        Resume[] storage2 = new Resume[10000];
        boolean ok = true;
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                ok = false;
            } else {
                if (ok) {
                    storage2[i] = storage[i];
                } else {
                    if (i == 0)
                        storage2[i] = storage[i];
                    else
                        storage2[i - 1] = storage[i];
                }
            }
        }
        storage = storage2;
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
        int i = 0;
        while (storage[i] != null) {
            i++;
        }
        return i;
    }
}
