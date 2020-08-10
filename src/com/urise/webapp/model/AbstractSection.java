package com.urise.webapp.model;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractSection<SK> implements Serializable {
    private static final long serialVersionUID = 1L;

    protected SK content;
    
    public AbstractSection(SK content){
        Objects.requireNonNull(content, "content must not be null!");
        this.content = content;
    }

    public SK getContent(){
        return content;
    }

    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractSection<SK> section = (AbstractSection<SK>) o;
        return content.equals(section.content);
    }

    public int hashCode() {
        return content.hashCode();
    }
}
