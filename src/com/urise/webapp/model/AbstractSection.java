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

    public abstract boolean equals(Object o);

    public int hashCode() {
        return content.hashCode();
    }
}
