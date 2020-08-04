package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected Resume getResume(File key) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files
            ) {
                if (key.equals(file)) {

                }
            }
        }
        return null;
    }

    @Override
    protected File getSearchKey(String uuid) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files
            ) {
                if (uuid.equals(file.getName())) {
                    return file;
                }
            }
        }
        return null;
    }

    @Override
    protected boolean isExistResume(File key) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files
            ) {
                if (key.equals(file)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected void removeResume(File key) {
        if (!key.delete()) {
            throw new StorageException(key.getName(), "File cannot be deleted");
        }

    }

    @Override
    protected void updateResume(File key, Resume resume) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files
            ) {
                if (key.equals(file)) {

                    try {
                        PrintWriter pw = new PrintWriter(file);
                        pw.write(resume.toString());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    protected void addResume(Resume resume, File key) {
        try {
            PrintWriter pw = new PrintWriter(key);
            pw.write(resume.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected List<Resume> copyAllResume() {
        return getAllSorted();
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }

    @Override
    public int size() {
        if (directory.list() == null) {
            throw new StorageException("Directory does not contain resume!", null);
        }
        return directory.list().length;
    }

    public abstract List<Resume> getAllSorted();
}
