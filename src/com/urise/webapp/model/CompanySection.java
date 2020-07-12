package com.urise.webapp.model;

import java.util.List;

public class CompanySection extends AbstractSection<List<CompanyContent>> {

    public CompanySection(List<CompanyContent> content) {
        super(content);
    }

    @Override
    public boolean compareContent(Object o) {
        CompanySection company = (CompanySection) o;
        return content.equals(company.content);
    }
}
