package lesson7.task1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static lesson7.task1.FilesKt.deleteMarked;

public class MyTests {

    private static final String IN_FILENAME = "in.txt";
    private static final String OUT_FILENAME = "out.txt";

    private static final String STRING_FOR_DELETION = "_Should be deleted";
    private static final String STRING_NOT_FOR_DELETION = "Should not be deleted";

    @After
    public void testTeardown() {
        File in = new File(IN_FILENAME);
        File out = new File(OUT_FILENAME);

        in.delete();
        out.delete();
    }

    @Test
    public void deleteMarked_shouldDeleteMarked() throws IOException {
        createFile(STRING_FOR_DELETION + "\n" + STRING_NOT_FOR_DELETION);
        deleteMarked(IN_FILENAME, OUT_FILENAME);
        assertFileContent(List.of(STRING_NOT_FOR_DELETION));
    }

    @Test
    public void deleteMarked_shouldDeleteLotsOfMarked() throws IOException {
        StringBuilder fileContent = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            fileContent.append(STRING_FOR_DELETION);
            fileContent.append("\n");
            fileContent.append(STRING_NOT_FOR_DELETION);
            fileContent.append("\n");
        }
        StringBuilder expected = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            expected.append(STRING_NOT_FOR_DELETION);
            expected.append("\n");
        }
        createFile(fileContent.toString());
        deleteMarked(IN_FILENAME, OUT_FILENAME);
        assertFileContent(Arrays.stream(expected.toString().split("\n")).collect(Collectors.toList()));
    }

    @Test
    public void deleteMarked_shouldNotDeleteNotMarked() throws IOException {
        createFile(STRING_NOT_FOR_DELETION);
        deleteMarked(IN_FILENAME, OUT_FILENAME);
        assertFileContent(List.of(STRING_NOT_FOR_DELETION));
    }

    @Test
    public void deleteMarked_shouldNotChangeEmptyFile() throws IOException {
        createFile("");
        deleteMarked(IN_FILENAME, OUT_FILENAME);
        assertFileContent(List.of());
    }

    private void createFile(String content) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(IN_FILENAME));
        bufferedWriter.write(content);
        bufferedWriter.close();
    }

    private void assertFileContent(List<String> expected) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(OUT_FILENAME));
        Assertions.assertEquals(expected, bufferedReader.lines().collect(Collectors.toList()));
    }

}
