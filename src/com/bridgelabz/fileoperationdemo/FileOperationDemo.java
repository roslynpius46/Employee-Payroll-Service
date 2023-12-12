package com.bridgelabz.fileoperationdemo;

import java.io.File;
import java.io.IOException;

public class FileOperationDemo {

    public static void main(String[] args) {
        try {
            // Specify file path and name
            String filePath = "example.txt";

            // Check if file exists
            boolean fileExists = checkFileExists(filePath);
            System.out.println("File exists: " + fileExists);

            // Create an empty file
            createEmptyFile(filePath);
            System.out.println("File created successfully.");

            // List files in the current directory
            listFilesAndDirectories(".");

            // List files with a specific extension
            listFilesWithExtension(".", "txt");

            // Delete the created file
            deleteFile(filePath);
            System.out.println("File deleted successfully.");

            // Check if the file no longer exists
            fileExists = checkFileExists(filePath);
            System.out.println("File exists: " + fileExists);

            // Create a directory
            createDirectory("new_directory");
            System.out.println("Directory created successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @desc Function to check if file exists
     * @param filePath path of file
     * @return True if file exists else false
     */
    private static boolean checkFileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * @desc Function to create an empty file
     * @param filePath path of file
     * @throws IOException in case of IO Exception     *
     */
    private static void createEmptyFile(String filePath) throws IOException {
        File file = new File(filePath);
        file.createNewFile();
    }

    /**
     * @desc Function to delete file
     * @param filePath path of file
     */
    private static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * @desc Function to create directory
     * @param directoryPath path of directory
     */
    private static void createDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        directory.mkdir();
    }

    /**
     * @desc function to list all the files and directories
     * @param directoryPath path of directory
     */
    private static void listFilesAndDirectories(String directoryPath) {
        File directory = new File(directoryPath);
        String[] files = directory.list();
        System.out.println("Files and directories in " + directoryPath + ":");
        for (String file : files) {
            System.out.println(file);
        }
    }

    /**
     * @desc function to list all the files and directories
     * @param directoryPath path of directory
     * @param extension extension of file
     */
    private static void listFilesWithExtension(String directoryPath, String extension) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.endsWith("." + extension));
        System.out.println("Files with extension ." + extension + " in " + directoryPath + ":");
        if (files != null) {
            for (File file : files) {
                System.out.println(file.getName());
            }
        }
    }
}
