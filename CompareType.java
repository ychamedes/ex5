import java.io.File;
import java.util.Comparator;

public class CompareType implements Comparator<File> {

    @Override
    public int compare(File o1, File o2) {
        String o1Type = o1.getName().substring(o1.getName().lastIndexOf(".") + 1);
        String o2Type = o2.getName().substring(o2.getName().lastIndexOf(".") + 1);

        return o1Type.compareTo(o2Type);
    }
}
