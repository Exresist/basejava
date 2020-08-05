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
    public boolean areEquals(Object o) {
        StringSection section = (StringSection) o;
        return content.equals(section.content);
    }

}
