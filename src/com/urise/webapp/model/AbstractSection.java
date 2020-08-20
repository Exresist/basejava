package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)

public abstract class AbstractSection<SK> implements Serializable {
    private static final long serialVersionUID = 1L;

    protected SK content;

    public AbstractSection() {

    }

    public AbstractSection(SK content) {
        Objects.requireNonNull(content, "content must not be null!");
        this.content = content;
    }

    public SK getContent() {
        return content;
    }

    public int hashCode() {
        return content.hashCode();
    }
}
