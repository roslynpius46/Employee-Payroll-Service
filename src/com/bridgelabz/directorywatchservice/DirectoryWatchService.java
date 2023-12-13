package com.bridgelabz.directorywatchservice;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class DirectoryWatchService {

    public static void main(String[] args) throws IOException {
        // Specify the directory to be watched
        Path directoryPath = Paths.get("watched_directory");

        // Set up a Watch Service
        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            // Register the directory with the Watch Service
            directoryPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

            System.out.println("Watching directory: " + directoryPath);

            // Infinite loop to watch for events
            while (true) {
                WatchKey key;
                try {
                    key = watchService.take();
                } catch (InterruptedException e) {
                    return;
                }

                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path entry = (Path) event.context();
                    Path fullPath = directoryPath.resolve(entry);

                    if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                        System.out.println("Entry created: " + fullPath);
                    } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                        System.out.println("Entry deleted: " + fullPath);
                    } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                        System.out.println("Entry modified: " + fullPath);
                    }

                    // Count the number of entries in the file (including files and subdirectories)
                    int entryCount = countEntries(directoryPath.toFile());
                    System.out.println("Number of entries in the file: " + entryCount);
                }

                // Reset the key to receive further events
                boolean valid = key.reset();
                if (!valid) {
                    // Directory is no longer accessible
                    break;
                }
            }
        }
    }

    public static int countEntries(File directory) {
        System.out.println("Checking directory: " + directory.getAbsolutePath());

        File[] files = directory.listFiles();
        int count = 0;

        if (files != null) {
            for (File file : files) {
                count++; // Count the current file or directory
                System.out.println("Entry: " + file.getAbsolutePath());

                if (file.isDirectory()) {
                    // Recursively count entries in subdirectories
                    int subdirectoryCount = countEntries(file);
                    System.out.println("Subdirectory count: " + subdirectoryCount);
                    count += subdirectoryCount;
                }
            }
        }

        return count;
    }

}

