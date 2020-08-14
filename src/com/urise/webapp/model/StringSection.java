package com.urise.webapp.model;

public class StringSection extends AbstractSection<String> {

    private static final long serialVersionUID = 1L;

    public StringSection(String content) {
        super(content);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringSection section = (StringSection) o;
        return content.equals(section.content);
    }

    @Override
    public String toString() {
        return content;
    }

}
