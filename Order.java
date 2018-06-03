import java.io.File;
import java.util.HashSet;

public interface Order {
    File[] sort(HashSet<File> files);
}
