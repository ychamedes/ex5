package filesprocessing;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * ReverseOrder class that functions as a decorator on a RegularOrder class, making them reverse their order.
 */
public class ReverseOrder implements Order {

    private RegularOrder order;

    /**
     * Constructor for a ReverseOrder instance.
     * @param order - the RegularOrder instance that is being reversed
     */
    ReverseOrder(RegularOrder order){
        this.order = order;
    }

    @Override
    public File[] sort(HashSet<File> files) {

        File[] orderedFiles = files.toArray(new File[files.size()]);
        Arrays.sort(orderedFiles, Collections.reverseOrder(order.comparator));

        return orderedFiles;
    }
}
