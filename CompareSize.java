import java.io.File;
import java.util.Comparator;

public class CompareSize implements Comparator<File> {

    private static final int K_BYTE_CONVERSION_FACTOR = 1024;

    @Override
    public int compare(File o1, File o2) {
        return (int) (o1.getTotalSpace() - o2.getTotalSpace() / K_BYTE_CONVERSION_FACTOR);
    }

}
