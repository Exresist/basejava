package com.urise.webapp.storage;

public class SqlStorageTest extends AbstractStorageTest  {
    public SqlStorageTest() {
        super(new SqlStorage("db.url", "db.user", "db.password"));
    }
}