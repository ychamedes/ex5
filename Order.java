package filesprocessing;

import java.io.File;
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
}
