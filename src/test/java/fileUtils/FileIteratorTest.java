package fileUtils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.UncheckedIOException;
import java.util.NoSuchElementException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class FileIteratorTest {

    static private String incorrectPath;
    static private String pathToEmptyFile;
    static private String pathToNonEmptyFile;
    static private String pathToPicture;
    FileIterator reader;

    @BeforeAll
    static void setup() {
        incorrectPath = "qwerty.txt";
        pathToEmptyFile = Objects.requireNonNull(FileIterator.class.getResource("/empty.txt")).getPath();
        pathToPicture = Objects.requireNonNull(FileIterator.class.getResource("/picture.bmp")).getPath();
        pathToNonEmptyFile = Objects.requireNonNull(FileIterator.class.getResource("/nonEmpty.txt")).getPath();
    }

    @Test
    void shouldThrowsNoSuchElementExceptionOnReadingEmptyFile() {
        assertThrows(NoSuchElementException.class, () -> new FileIterator(pathToEmptyFile).next());
    }

    @Test
    void shouldThrowsFileNotFoundExceptionWhenIteratorIsCreatedOnNonExistingFile() {
        assertThrows(FileNotFoundException.class, () -> new FileIterator(incorrectPath));
    }

    @Test
    void hasNextShouldReturnFalseWhenTheEndOfTheFileHasReached() throws FileNotFoundException {
        reader = new FileIterator(pathToEmptyFile);
        assertFalse(reader.hasNext());
    }

    @Test
    void hasNextShouldReturnTrueWhenTheEndOfTheFileHasNotReached() throws FileNotFoundException {
        reader = new FileIterator(pathToNonEmptyFile);
        assertTrue(reader.hasNext());
    }

    @Test
    void shouldThrowNothingOnClosingAnAlreadyClosedFileIterator() throws FileNotFoundException {
        reader = new FileIterator(pathToPicture);
        reader.close();
        assertDoesNotThrow(() -> reader.close());
    }

    @Test
    void shouldThrowUncheckedIOExceptionWhenHasNextMethodIsInvokeViaClosedFileIterator() throws FileNotFoundException {
        reader = new FileIterator(pathToPicture);
        reader.close();
        assertThrows(UncheckedIOException.class, () -> reader.hasNext());
    }

    @Test
    void shouldThrowUncheckedIOExceptionReadingViaClosedFileIterator() throws FileNotFoundException {
        reader = new FileIterator(pathToPicture);
        reader.close();
        assertThrows(UncheckedIOException.class, () -> reader.next());
    }

    @Test
    void checkMethodNextThatOutStringIsCorrect() throws FileNotFoundException {
        reader = new FileIterator(pathToNonEmptyFile);
        assertEquals("громко и четко я могу говорить давай я могу говорить", reader.next());
    }

    @AfterEach
    void cleanResources() {
        if (reader != null)
            reader.close();
    }


}