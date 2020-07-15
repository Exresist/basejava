package com.urise.webapp.model;

import java.util.List;
import java.util.stream.Collectors;

public class CompanySection extends AbstractSection<List<Experience>> {

    public CompanySection(List<Experience> content) {
        super(content);
    }

    @Override
    public String toString() {
        return content.stream().map(Experience::toString).collect(Collectors.joining());
    }
}
