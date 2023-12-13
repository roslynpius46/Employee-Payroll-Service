import com.bridgelabz.directorywatchservice.DirectoryWatchService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectoryWatchServiceTest {

    @Test
    public void testCountEntries(@TempDir Path tempDir) throws IOException {
        // Create a temporary directory structure
        Path dir1 = tempDir.resolve("dir1");
        Path dir2 = tempDir.resolve("dir2");
        Path file1 = tempDir.resolve("file1.txt");
        Path file2 = dir1.resolve("file2.txt");
        Path file3 = dir2.resolve("file3.txt");

        Files.createDirectories(dir1);
        Files.createDirectories(dir2);
        Files.createFile(file1);
        Files.createFile(file2);
        Files.createFile(file3);

        // Test the countEntries method
        int entryCount = DirectoryWatchService.countEntries(tempDir.toFile());
        assertEquals(5, entryCount); // 4 entries: tempDir, dir1, dir2, file1.txt

        // Add more files and test again
        Path file4 = dir1.resolve("file4.txt");
        Path file5 = dir2.resolve("file5.txt");
        Files.createFile(file4);
        Files.createFile(file5);

        entryCount = DirectoryWatchService.countEntries(tempDir.toFile());
        assertEquals(7, entryCount); // 6 entries: tempDir, dir1, dir2, file1.txt, file2.txt, file3.txt
    }
}
