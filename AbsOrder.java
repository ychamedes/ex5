import java.io.File;
import java.util.Comparator;

/**
 * AbsOrder class used to order a list of files alphanumerically by absolute path.
 */
public class AbsOrder extends RegularOrder {

    /**
     * Default constructor for a AbsOrder instance.
     */
    AbsOrder() {
        this.comparator = new CompareAbs();
    }

    /**
     * A local Comparator class that compares the absolute path of two files.
     */
    private class CompareAbs implements Comparator<File> {

        @Override
        public int compare(File o1, File o2) {
            return o1.getAbsolutePath().compareTo(o2.getAbsolutePath());
        }
    }
}
