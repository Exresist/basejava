package com.urise.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class CompanyPositions implements Serializable {
    private static final long serialVersionUID = 1L;

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String text;

    public CompanyPositions(LocalDate startDate, LocalDate endDate, String title, String text) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.text = text;
    }

    @Override
    public String toString() {
        return startDate + " " + endDate + " " + title + " " + text;
    }

    @Override
    public int hashCode(){
        int result = startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + text.hashCode();
        return result;
    }
}