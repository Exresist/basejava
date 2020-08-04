package com.urise.webapp.model;

import java.util.List;

public class Experience {
    private final Link homePage;
    private final List<CompanyPositions> companyPositions;

    public Experience(String name, String url, List<CompanyPositions> companyPositions) {

        this.homePage = new Link(name, url);
        this.companyPositions = companyPositions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Experience company = (Experience) o;

        if (!homePage.equals(company.homePage)) return false;
        return companyPositions.equals(company.companyPositions);

    }

    @Override
    public String toString() {
        return homePage + " " + companyPositions.toString();
    }

    @Override
    public int hashCode() {
        int result = companyPositions.hashCode();
        result = 31 * result + homePage.hashCode();
        return result;
    }
}
