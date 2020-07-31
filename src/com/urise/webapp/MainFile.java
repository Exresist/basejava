package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) throws IOException {
        String filePath = ".\\.gitignore";
        File file = new File(filePath);
        System.out.println(file.getCanonicalPath());
        File dir = new File("./src/com/urise/webapp");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File directory = new File("C:\\Users\\1\\basejava");
        PrintFiles(directory);

    }

    public static void PrintFiles(File directory) {
        if (directory.isDirectory()) {
            System.out.println("Directory:" + directory.getName());
            for (File file: directory.listFiles()
                 ) {
                PrintFiles(file);
            }
        } else{
            System.out.println("File" + directory.getName());
        }
    }
}