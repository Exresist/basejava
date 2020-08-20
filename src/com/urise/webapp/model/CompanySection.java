package com.urise.webapp.model;

public class CompanySection extends AbstractSection<Company> {

    private static final long serialVersionUID = 1L;

    public CompanySection() {

    }

    public CompanySection(Company content) {
        super(content);
    }

    @Override
    public String toString() {
        return content.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanySection section = (CompanySection) o;
        return content.equals(section.content);
    }
}
