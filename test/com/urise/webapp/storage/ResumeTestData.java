package com.urise.webapp.storage;

import com.urise.webapp.model.*;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

public class ResumeTestData{

    @Test
    public void main(){
        Resume resume = new Resume("Dummy");
        resume.addContact(ContactType.EMAIL, new StringSection("AlexClanc@loh.sosu"));
        resume.addContact(ContactType.GITHUB, new StringSection("AlexClanc"));
        resume.addContact(ContactType.HOME_PAGE, new StringSection("google.com"));
        resume.addContact(ContactType.LINKEDIN, new StringSection("Otvernis"));
        resume.addContact(ContactType.PHONE_NUMBER, new StringSection("+123454333"));
        resume.addContact(ContactType.SKYPE, new StringSection("AlexClanc123"));
        resume.addContact(ContactType.STACKOVERFLOW, new StringSection("Dima"));

        resume.addSection(SectionType.PERSONAL, new StringSection("Hackerman"));
        resume.addSection(SectionType.OBJECTIVE, new StringSection("Hackerman"));
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList("pivo", "sex", "sanya", "itmo")));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection(Arrays.asList("pivo123", "sex123", "sanya123", "itmo123")));
        resume.addSection(SectionType.EXPERIENCE, new CompanySection(Arrays.asList(new Experience(LocalDate.now(), LocalDate.now(), "Google", "Vse sdelal", "Rabota klevaya"))));
        resume.addSection(SectionType.EDUCATION, new CompanySection(Arrays.asList(new Experience(LocalDate.now(), LocalDate.now(), "Google", "Vse sdelal", "Rabota klevaya"))));

        resume.getSections().forEach((k, v) -> System.out.println("Type: " + k + " value: " + v));

        resume.getContacts().forEach((k, v) -> System.out.println("ContactType: " + k + " value: " + v));
        }
}
