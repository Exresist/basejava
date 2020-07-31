package com.urise.webapp.model;

import java.util.List;

public class Experience {
    private final Link homePage;
    private final String companyName;
    private final List<CompanyPositions> companyPositions;

    public Experience(String name, String url, String companyName, List<CompanyPositions> companyPositions) {

        this.homePage = new Link(name, url);
        this.companyName = companyName;
        this.companyPositions = companyPositions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Experience company = (Experience) o;

        if (!homePage.equals(company.homePage)) return false;
        if (!companyName.equals(company.companyName)) return false;
        return companyPositions.equals(company.companyPositions);

    }

    @Override
    public String toString() {
        return companyName + " " + homePage + " " + companyPositions.toString();
    }

    @Override
    public int hashCode() {
        int result = companyPositions.hashCode();
        result = 31 * result + homePage.hashCode();
        result = 31 * result + companyName.hashCode();
        return result;
    }
}
