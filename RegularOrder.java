import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class RegularOrder implements Order {

    protected Comparator<File> comparator;

    RegularOrder(Comparator<File> comparator) {
        this.comparator = comparator;
    }

    public File[] sort(HashSet<File> files) {

        File[] orderedFiles = (File[]) files.toArray();
        Arrays.sort(orderedFiles, comparator);

        return orderedFiles;
    }
}
