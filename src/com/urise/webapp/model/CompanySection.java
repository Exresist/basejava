package com.urise.webapp.model;

import java.util.List;
import java.util.stream.Collectors;

public class CompanySection extends AbstractSection<List<Experience>> {

    public CompanySection(List<Experience> content) {
        super(content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanySection section = (CompanySection) o;
        return content.equals(section.content);
    }

    @Override
    public String toString() {
        return content.stream().map(Experience::toString).collect(Collectors.joining());
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }
}
