package FileUtils;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FileIterator implements Iterator<String>, AutoCloseable {

    private BufferedReader reader;

    public FileIterator(String path) {
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }

    @Override
    public boolean hasNext() {
        try {
            return reader.ready();
        } catch (IOException e) {
            close();
            return false;
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
