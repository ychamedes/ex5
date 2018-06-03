import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

/**
 * RegularOrder class, which is an abstract class that sorts sets of files into an ordered array.
 */
public abstract class RegularOrder implements Order {

    /** Specific comparator used by subclass in sort. */
    protected Comparator<File> comparator;

    @Override
    public File[] sort(HashSet<File> files) {

        File[] orderedFiles = (File[]) files.toArray();
        Arrays.sort(orderedFiles, comparator);

        return orderedFiles;
    }
}
