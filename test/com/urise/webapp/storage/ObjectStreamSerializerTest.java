package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.ObjectStreamSerializer;

public class ObjectStreamSerializerTest extends AbstractStorageTest  {
    public ObjectStreamSerializerTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializer()));
    }
}