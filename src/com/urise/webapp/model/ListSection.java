package com.urise.webapp.model;

import java.util.List;

public class ListSection extends AbstractSection<List<String>> {

    private static final long serialVersionUID = 1L;

    public ListSection(List<String> content) {
        super(content);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection section = (ListSection) o;
        return content.equals(section.content);
    }

    @Override
    public String toString() {
        return String.join("\n", content);
    }
}