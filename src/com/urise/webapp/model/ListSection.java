package com.urise.webapp.model;

import java.util.List;
import java.util.stream.Collectors;

public class ListSection extends AbstractSection<List<String>> {

    public ListSection(List<String> content) {
        super(content);
    }

    @Override
    public String toString() {
        return String.join("\n", content);
    }
}