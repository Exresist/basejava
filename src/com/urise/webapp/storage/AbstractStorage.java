package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    protected static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);


    public void update(Resume resume) {
        LOG.info("update" + resume);
        SK searchKey = getExistedSearchKey(resume.getUuid());
        updateResume(searchKey, resume);
    }

    public void save(Resume resume) {
        LOG.info("save" + resume);
        SK searchKey = getNotExistedSearchKey(resume.getUuid());
        addResume(resume, searchKey);
    }

    public Resume get(String uuid) {
        LOG.info("get" + uuid);
        SK searchKey = getExistedSearchKey(uuid);
        return getResume(searchKey);
    }

    public void delete(String uuid) {
        LOG.info("delete" + uuid);
        SK searchKey = getExistedSearchKey(uuid);
        removeResume(searchKey);
    }

    private SK getExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExistResume(searchKey)) {
            LOG.warning("ERROR! Storage does not contain " + uuid + "!");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExistResume(searchKey)) {
            LOG.warning("ERROR! Storage already contain" + uuid + "!");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> list = copyAllResume();
        list.sort(RESUME_COMPARATOR);
        return list;
    }

    protected abstract Resume getResume(SK key);

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isExistResume(SK key);

    protected abstract void removeResume(SK key);

    protected abstract void updateResume(SK key, Resume resume);

    protected abstract void addResume(Resume resume, SK key);

    protected abstract List<Resume> copyAllResume();
}
