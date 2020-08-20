package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.UUID;

public class ResumeTestData {
    public static Resume getInstance(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.addContact(ContactType.EMAIL, "AlexClanc@rere");
        resume.addContact(ContactType.GITHUB, "AlexClanc.com");
        resume.addContact(ContactType.HOME_PAGE, "google.com");
        resume.addContact(ContactType.LINKEDIN, "Otvernis");
        resume.addContact(ContactType.PHONE_NUMBER, "+123454333");
        resume.addContact(ContactType.SKYPE, "AlexClanc123");
        resume.addContact(ContactType.STACKOVERFLOW, "Dima");

        resume.addSection(SectionType.PERSONAL, new StringSection("Hackerman"));
        resume.addSection(SectionType.OBJECTIVE, new StringSection("Hackerman"));
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection("sss1231", "ss", "sanya", "HSE"));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection("sss123", "s21", "sanya123", "HSE123"));
        resume.addSection(SectionType.EXPERIENCE, new CompanySection(new Company("homePage", "www.google.com", new CompanyPositions(LocalDate.MIN, LocalDate.now(), "Vse sdelal", "Rabota klevaya"), new CompanyPositions(LocalDate.MIN, LocalDate.now(), "Vse sdelal", "Rabota klevaya"))));
        resume.addSection(SectionType.EDUCATION, new CompanySection(new Company("homePageNumTwo", "www.yandex.ru", new CompanyPositions(LocalDate.MIN, LocalDate.now(), "Vse sdelal", "Rabota klevaya"))));
        return resume;
    }


    public static void main(String[] args) {
        Resume resume = getInstance(UUID.randomUUID().toString(), "Dummy");
        resume.getSections().forEach((k, v) -> System.out.println("Type: " + k + " value: " + v));
        resume.getContacts().forEach((k, v) -> System.out.println("ContactType: " + k + " value: " + v));
    }
}
