package com.urise.webapp.storage;

import static org.junit.Assert.*;

public class ArrayStorageTest extends AbstractArrayStorageTest{

    private Storage sortedStorage = new SortedArrayStorage();
    public ArrayStorageTest(Storage storage) {
        super(storage);
    }
}