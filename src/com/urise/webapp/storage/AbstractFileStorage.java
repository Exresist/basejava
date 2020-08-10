package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
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
        try {
            return doRead(new BufferedInputStream(new FileInputStream(key)));
        } catch (IOException e) {
           throw new StorageException("File read error", key.getName(), e);
        }
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
                    try {
                        doWrite(resume, new BufferedOutputStream(new FileOutputStream(key)));
                    } catch (IOException e) {
                        e.printStackTrace();
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

    protected abstract void doWrite(Resume resume, OutputStream os) throws IOException;

    protected abstract Resume doRead(InputStream is) throws IOException;
}
