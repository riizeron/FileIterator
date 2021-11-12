package FileUtils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class FileIteratorTest {

    static private String incorrectPath;
    static private String pathToEmptyFile;
    @BeforeAll
    static void setup(){
        incorrectPath = "\\asdas\\asdfsd\\adf\\sd\\fs0.txt";
        pathToEmptyFile = "D:\\JetBrains\\Java\\FileIteratorWithTests\\src\\empty.txt";
    }

    @Test
    void shouldThrowsNoSuchElementExceptionWhenReadEmptyFile(){
        assertThrows(NoSuchElementException.class, ()->{new FileIterator(pathToEmptyFile).next();});
    }
    @Test
    void checkIteratorIsNullOnNotExistingPath(){
        assertTrue(new FileIterator(incorrectPath) == null);
    }
    //@Test
    //void shouldCloseProgramOnANotExistingPath() {
    //    assert(FileNotFoundException.class, ()->{new FileIterator(incorrectPath);});
    //}



}