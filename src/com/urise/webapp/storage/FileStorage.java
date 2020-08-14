package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private File directory;
    private StrategySerialization strategySerialization;

    protected FileStorage(File directory, StrategySerialization strategySerialization) {
        Objects.requireNonNull(directory, "directory must not be null");
        this.strategySerialization = strategySerialization;
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected Resume getResume(File file) {
        try {
            return strategySerialization.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExistResume(File file) {
        return file.exists();
    }

    @Override
    protected void removeResume(File file) {
        if (!file.delete()) {
            throw new StorageException(file.getName(), "File cannot be deleted");
        }

    }

    @Override
    protected void updateResume(File files, Resume resume) {
        try {
            strategySerialization.doWrite(resume, new BufferedOutputStream(new FileOutputStream(files)));
        } catch (IOException e) {
            throw new StorageException("Update error " + files.getAbsolutePath(), files.getName(), e);
        }
    }

    @Override
    protected void addResume(Resume resume, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
        updateResume(file, resume);
    }

    @Override
    protected List<Resume> copyAllResume() {
        File[] files = filesList();
        List<Resume> list = new ArrayList<>(files.length);
        for (File file : files) {
            list.add(getResume(file));
        }
        return list;
    }

    @Override
    public void clear() {
        File[] files = filesList();
        for (File file : files) {
            file.delete();
        }
    }

    @Override
    public int size() {
        if (directory.list() == null) {
            throw new StorageException("Directory does not contain resume!", null);
        }
        return Objects.requireNonNull(directory.list()).length;
    }

    private File[] filesList() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory read error", null);
        }
        return files;
    }

}
