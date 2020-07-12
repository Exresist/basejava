package com.urise.webapp.model;

import java.util.List;

public class ListSection extends AbstractSection<List<String>> {

    public ListSection(List<String> content) {
        super(content);
    }

    @Override
    public boolean compareContent(Object o) {
        ListSection list = (ListSection) o;
        
        return content.equals(list.content);
    }
}
