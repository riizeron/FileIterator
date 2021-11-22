import fileUtils.FileIterator;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {

        try (FileIterator fi = new FileIterator("q.txt");) {
            while (fi.hasNext()) {
                System.out.println(fi.next());
            }
        } catch (NoSuchElementException | FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}

