package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

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
        File directory = new File("C:\\Users\\gpaho\\basejava");
        System.out.println(System.nanoTime());
        printFiles(directory, "");
        System.out.println(System.nanoTime());
    }

    public static void printFiles(File directory, String space) {
        if (directory.isDirectory()) {
            System.out.println(space + "Directory:" + directory.getName());
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                printFiles(file, space + " ");
            }
        } else {
            System.out.println(space + "File" + directory.getName());
        }
    }
}
