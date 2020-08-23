package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataStreamSerializer implements StrategySerialization {

    @Override
    public void doWrite(Resume resume, OutputStream file) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(file)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            writeInfo(dos, resume.getContacts().entrySet(), dataStreamWriter -> {
                dos.writeUTF(dataStreamWriter.getKey().name());
                dos.writeUTF(dataStreamWriter.getValue());
            });

            writeInfo(dos, resume.getSections().entrySet(), dst -> {
                dos.writeUTF(dst.getKey().name());
                switch (dst.getKey()) {
                    case PERSONAL:
                    case OBJECTIVE: {
                        dos.writeUTF(((StringSection) dst.getValue()).getContent());
                        break;
                    }
                    case ACHIEVEMENT:
                    case QUALIFICATIONS: {
                        writeInfo(dos, ((ListSection) dst.getValue()).getItems(), dos::writeUTF);
                        break;
                    }
                    case EDUCATION:
                    case EXPERIENCE: {
                        writeInfo(dos, ((CompanySection) dst.getValue()).getCompanies(), company -> {
                            dos.writeUTF((company.getHomePage().getName()));
                            dos.writeUTF(company.getHomePage().getUrl());
                            writeInfo(dos, company.getCompanyPositions(), position -> {
                                dos.writeInt(position.getStartDate().getYear());
                                dos.writeInt(position.getStartDate().getMonthValue());
                                dos.writeInt(position.getStartDate().getDayOfMonth());
                                dos.writeInt(position.getEndDate().getYear());
                                dos.writeInt(position.getEndDate().getMonthValue());
                                dos.writeInt(position.getEndDate().getDayOfMonth());
                                dos.writeUTF(position.getTitle());
                                dos.writeUTF(position.getText());
                            });
                        });
                        break;
                    }
                    default:
                        throw new IllegalStateException();
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readInfo(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readInfo(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(dis, sectionType));
            });
            return resume;
        }
    }

    private interface DataStreamWriter<T> {
        void write(T t) throws IOException;
    }

    private interface DataStreamReader {
        void read() throws IOException;
    }

    private void readInfo(DataInputStream dis, DataStreamReader dsr) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            dsr.read();
        }
    }

    private AbstractSection readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new StringSection(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                return new ListSection(readList(dis, dis::readUTF));
            case EXPERIENCE:
            case EDUCATION:
                return new CompanySection(
                        readList(dis, () -> new Company(
                                new Link(dis.readUTF(), dis.readUTF()),
                                readList(dis, () -> new CompanyPositions(
                                        LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt()),
                                        LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt()), dis.readUTF(), dis.readUTF())
                                ))));
            default:
                return null;
        }
    }

    private interface ElementReader<T> {
        T read() throws IOException;
    }

    private <T> void writeInfo(DataOutputStream dos, Collection<T> collection, DataStreamWriter<T> writer) throws
            IOException {
        dos.writeInt(collection.size());
        for (T item : collection
        ) {
            writer.write(item);
        }
    }

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }
}
