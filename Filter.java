package filesprocessing;

import java.io.File;
import java.util.HashSet;

public interface Filter {

    HashSet<File> sort(File[] files);

}
