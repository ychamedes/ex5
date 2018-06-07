package filesprocessing;

import java.io.File;
import java.util.Comparator;

/**
 * SizeOrder class used to order a list of files by size.
 */
public class SizeOrder extends RegularOrder {

    /**
     * Default constructor for a SizeOrder instance.
      */
    SizeOrder() {
        this.comparator = new CompareSize();
    }

    /**
     * A local Comparator class that compares the sizes of two files.
     */
    private class CompareSize implements Comparator<File> {

        private static final int K_BYTE_CONVERSION_FACTOR = 1024;

        @Override
        public int compare(File o1, File o2) {
            return (int) (o1.getTotalSpace() - o2.getTotalSpace() / K_BYTE_CONVERSION_FACTOR);
        }

    }
}
