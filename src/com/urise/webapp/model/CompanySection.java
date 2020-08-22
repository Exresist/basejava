package com.urise.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CompanySection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private List<Company> Companies;

    public CompanySection() {}

    public CompanySection(Company... Companies) {
        this(Arrays.asList(Companies));
    }

    public CompanySection(List<Company> Companies) {
        Objects.requireNonNull(Companies, "Companies must not be null");
        this.Companies = Companies;
    }

    public List<Company> getCompanies() {
        return Companies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanySection that = (CompanySection) o;

        return Companies.equals(that.Companies);

    }

    @Override
    public int hashCode() {
        return Companies.hashCode();
    }

    @Override
    public String toString() {
        return Companies.toString();
    }
}
