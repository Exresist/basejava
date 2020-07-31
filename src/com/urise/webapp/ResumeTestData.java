package com.urise.webapp;

import com.urise.webapp.model.*;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

public class ResumeTestData {
    public static Resume CreateData(String uuid, String fullName) {
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
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList("sss1231", "ss", "sanya", "HSE")));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection(Arrays.asList("sss123", "s21", "sanya123", "HSE123")));
        resume.addSection(SectionType.EXPERIENCE, new CompanySection(Collections.singletonList(new Experience("homePage", "www.google.com", "Google", Collections.singletonList(new CompanyPositions(LocalDate.MIN, LocalDate.now(), "Vse sdelal", "Rabota klevaya"))))));
        resume.addSection(SectionType.EDUCATION, new CompanySection(Collections.singletonList(new Experience("homePageNumTwo", "www.yandex.ru", "Yandex", Collections.singletonList(new CompanyPositions(LocalDate.MIN, LocalDate.now(), "Vse sdelal", "Rabota klevaya"))))));
        return resume;
    }

    @Test
    public void Main() {
        Resume resume = CreateData(UUID.randomUUID().toString(), "Dummy");
        resume.getSections().forEach((k, v) -> System.out.println("Type: " + k + " value: " + v));
        resume.getContacts().forEach((k, v) -> System.out.println("ContactType: " + k + " value: " + v));
    }
}
