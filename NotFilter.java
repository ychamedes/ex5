import java.io.File;
import java.util.HashSet;

public class NotFilter implements Filter {

    private Filter filter;
    private HashSet<File> filteredSet;
    private HashSet<File> negatedSet = new HashSet<File>();

    NotFilter(Filter filter){
        this.filter = filter;
    }

    public HashSet<File> sort(File[] files){
        filteredSet = filter.sort(files);
        for(File file: files){
            if(!filteredSet.contains(file))
                negatedSet.add(file);
        }
        return negatedSet;
    }
}
