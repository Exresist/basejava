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
    public boolean equals(Object o){
        return content.equals(o);
    }
}
