package com.urise.webapp.model;

import java.util.List;
import java.util.stream.Collectors;

public class CompanySection extends AbstractSection<List<Experience>> {

    private static final long serialVersionUID = 1L;

    public CompanySection(List<Experience> content) {
        super(content);
    }

    @Override
    public String toString() {
        return content.stream().map(Experience::toString).collect(Collectors.joining());
    }

    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanySection section = (CompanySection) o;
        return content.equals(section.content);
    }
}
