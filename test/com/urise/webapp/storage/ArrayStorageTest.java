package com.urise.webapp.storage;

import static org.junit.Assert.*;

public class ArrayStorageTest extends AbstractArrayStorageTest {
    private Storage arrayStorage = new ArrayStorage();

    public ArrayStorageTest(Storage storage) {
        super(storage);
    }
}