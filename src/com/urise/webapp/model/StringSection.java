package com.urise.webapp.model;

public class StringSection extends AbstractSection<String> {

    public StringSection(String content) {
        super(content);
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StringSection section = (StringSection) o;
        return content.equals(section.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }
}
