package filesprocessing;

import java.io.File;
import java.util.HashSet;

/**
 * Filter interface for filter classes (each sorts its own way).
 */
public interface Filter {

    /**
     * Filters an array of files into a set.
     * @param files the unfiltered array of files.
     * @return the filtered set of files.
     */
    HashSet<File> sort(File[] files);
}
