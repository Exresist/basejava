package com.urise.webapp.model;

import java.util.Objects;

public abstract class AbstractSection<SK> {
    protected SK content;
    
    public AbstractSection(SK content){
        Objects.requireNonNull(content, "content must not be null!");
        this.content = content;
    }

    public SK getContent(){
        return content;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return compareContent(o);
    }

    public abstract boolean compareContent(Object o);
}
