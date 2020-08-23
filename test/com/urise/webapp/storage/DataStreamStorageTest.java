package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.DataStreamSerializer;

public class DataStreamStorageTest extends AbstractStorageTest {
    public DataStreamStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamSerializer()));
    }
}