package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Experience {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String companyName;
    private final String title;
    private final String text;

    public Experience(LocalDate startDate, LocalDate endDate, String companyName, String title, String text) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(companyName, "endDate must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.companyName = companyName;
        this.title = title;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Experience company = (Experience) o;

        if (!startDate.equals(company.startDate)) return false;
        if (!endDate.equals(company.endDate)) return false;
        if (!companyName.equals(company.companyName)) return false;
        if (!title.equals(company.title)) return false;
        return Objects.equals(text, company.text);

    }

    @Override
    public String toString() {
        return title + ": " + text + " " + companyName + " " + startDate + " " + endDate;
    }
}
