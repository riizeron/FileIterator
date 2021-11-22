package fileUtils;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class FileIterator implements Iterator<String>, AutoCloseable {

    private final BufferedReader reader;

    public FileIterator(String path) throws FileNotFoundException {
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public boolean hasNext() {
        try {
            return reader.ready();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
