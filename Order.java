package filesprocessing;

import java.io.File;
import java.util.Comparator;
import java.util.HashSet;

/**
 * Order interface for order classes (each sorts its own way).
 */
public interface Order {

    /**
     * Sorts a set of files into an ordered array.
     * @param files the set of files to be sorted.
     * @return the sorted array of files.
     */
    File[] sort(HashSet<File> files);

    /**
     * A general Comparator class that compares the absolute path of two files.
     * Used by all orders to sort files within their individual ordering criteria.
     */
    public static class CompareAbs implements Comparator<File> {

        @Override
        public int compare(File o1, File o2) {
            return o1.getAbsolutePath().compareTo(o2.getAbsolutePath());
        }
    }
}
