package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;

    private Link homePage;

    private List<CompanyPosition> companyPositions;

    public Company() {
    }

    public Company(String name, String url, CompanyPosition... companyPositions) {
        this(new Link(name, url), Arrays.asList(companyPositions));
    }

    public Company(Link homePage, List<CompanyPosition> positions) {
        this.homePage = homePage;
        this.companyPositions = positions;
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<CompanyPosition> getCompanyPositions() {
        return companyPositions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (!homePage.equals(company.homePage)) return false;
        return companyPositions.equals(company.companyPositions);

    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(homePage + " ");
        for (CompanyPosition companyPos : companyPositions) {
            s.append(companyPos.toString()).append(" ");
        }
        return s.toString();

    }

    @Override
    public int hashCode() {
        int result = companyPositions.hashCode();
        result = 31 * result + homePage.hashCode();
        return result;
    }
}


