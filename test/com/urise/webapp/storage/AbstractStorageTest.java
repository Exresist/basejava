package com.urise.webapp.storage;

import com.urise.webapp.Config;
import com.urise.webapp.ResumeTestData;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public abstract class AbstractStorageTest {

    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;

    static {
        /*RESUME_1 = new Resume(UUID_1, "1");
        RESUME_2 = new Resume(UUID_2, "2");
        RESUME_3 = new Resume(UUID_3, "3");*/
        RESUME_1 = ResumeTestData.getInstance(UUID_1, "1");
        RESUME_2 = ResumeTestData.getInstance(UUID_2, "2");
        RESUME_3 = ResumeTestData.getInstance(UUID_3, "3");
    }

    public AbstractStorageTest(Storage Storage) {
        this.storage = Storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        assertEquals(2, storage.size());
        storage.get(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void save() {
        String UUID_4 = "uuid4";
        Resume RESUME_4 = ResumeTestData.getInstance(UUID_4, "4");
        storage.save(RESUME_4);
        assertEquals(4, storage.size());
        try {
            storage.get(UUID_4);
        } catch (Exception e) {
            fail("Save error!");
        }
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_1, "2");
        storage.update(resume);
            assertEquals(resume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void get() {
        assertEquals(RESUME_1, storage.get(RESUME_1.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void getAllSorted() {
        List<Resume> actualResumes = storage.getAllSorted();
        assertEquals(3, actualResumes.size());
        Assert.assertEquals(Arrays.asList(RESUME_1, RESUME_2, RESUME_3), actualResumes);
    }
}