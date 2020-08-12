package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PathStorage extends AbstractStorage<Path> {

    private Path directory;
    private StrategySerialization strategySerialization;

    protected PathStorage(String dir, StrategySerialization strategySerialization) {
        directory = Paths.get(dir);
        this.strategySerialization = strategySerialization;
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + "is not directory or not writeable!");
        }
    }

    @Override
    protected Resume getResume(Path key) {
        try {
            return strategySerialization.doRead(Files.newInputStream(key));
        } catch (IOException e) {
            throw new StorageException("Path read error", key.getFileName().toString(), e);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExistResume(Path key) {
        return Files.exists(key);
    }

    @Override
    protected void removeResume(Path key) {
        try {
            Files.deleteIfExists(key);
        } catch (IOException e) {
            throw new StorageException(key.getFileName().toString(), "Path cannot be deleted");
        }

    }

    @Override
    protected void updateResume(Path key, Resume resume) {
        try {
            strategySerialization.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(key)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void addResume(Resume resume, Path key) {
        try {
            Files.createFile(key);
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + key, key.getFileName().toString(), e);
        }
        updateResume(key, resume);
    }

    @Override
    protected List<Resume> copyAllResume() {
        try {
            List<Path> paths = Files.list(directory).collect(Collectors.toList());
            List<Resume> list = new ArrayList<>(paths.size());
            for (Path path : paths) {
                list.add(getResume(path));
            }
            return list;
        } catch (IOException e) {
            throw new StorageException("Directory read error", null);
        }

    }

    @Override
    public void clear() {
        try {
            Files.list(directory).parallel().forEach(this::removeResume);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {
        try {
            return (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("Path is empty", null);
        }
    }
}
