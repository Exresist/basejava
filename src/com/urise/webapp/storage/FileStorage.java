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
    protected Resume getResume(File files) {
        try {
            return strategySerialization.doRead(new BufferedInputStream(new FileInputStream(files)));
        } catch (IOException e) {
            throw new StorageException("File read error", files.getName(), e);
        }
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExistResume(File files) {
        return files.exists();
    }

    @Override
    protected void removeResume(File files) {
        if (!files.delete()) {
            throw new StorageException(files.getName(), "File cannot be deleted");
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
    protected void addResume(Resume resume, File files) {
        try {
            files.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + files.getAbsolutePath(), files.getName(), e);
        }
        updateResume(files, resume);
    }

    @Override
    protected List<Resume> copyAllResume() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory read error", null);
        }
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
