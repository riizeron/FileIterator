package fileUtils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class FileIteratorTest {

    static private String incorrectPath;
    static private String pathToEmptyFile;
    @BeforeAll
    static void setup(){
        incorrectPath = "\\asdas\\asdfsd\\adf\\sd\\fs0.txt";
        pathToEmptyFile = FileIterator.class.getResource("empty.txt").getPath();
    }

    @Test
    void shouldThrowsNoSuchElementExceptionWhenReadEmptyFile(){
        assertThrows(NoSuchElementException.class, ()->{new FileIterator(pathToEmptyFile).next();});
    }




}