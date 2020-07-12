package com.urise.webapp.model;

import java.util.Objects;

public class StringSection extends AbstractSection<String> {

    private StringSection(String content) {
        super(content);
    }

    @Override
    public boolean compareContent(Object o) {

        StringSection string = (StringSection) o;

        return content.equals(string.content);
    }
}
