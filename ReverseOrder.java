import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class ReverseOrder implements Order {

    private RegularOrder order;

    ReverseOrder(RegularOrder order){
        this.order = order;
    }

    @Override
    public File[] sort(HashSet<File> files) {

        File[] orderedFiles = (File[]) files.toArray();
        Arrays.sort(orderedFiles, Collections.reverseOrder(order.comparator));

        return orderedFiles;
    }
}
