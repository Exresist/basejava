package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.jupiter.api.Test;



public class AbstractArrayStorageTest {
    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    @Before
    public void setUp()  {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void delete() {
    }

    @Test
    public void save() {
    }

    @Test
    public void clear() {
    }

    @Test
    public void update() {
    }

    @Test
    public void size() {
    }

    @Test
    public void get() {
    }

    @Test
    public void getAll() {
    }
}