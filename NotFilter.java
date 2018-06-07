package filesprocessing;

import java.io.File;
import java.util.HashSet;

/**
 * NotFilter class that functions as a decorator on a filter class, returning the opposite files.
 */
public class NotFilter implements Filter {

    private Filter filter;
    private HashSet<File> filteredSet;
    private HashSet<File> negatedSet = new HashSet<>();

    /**
     * NotFilter constructor that receives a filter.
     * @param filter the filter to decorate.
     */
    NotFilter(Filter filter){
        this.filter = filter;
    }

    /**
     * Return a filtered set of files using the opposite of the what decorated class would return.
     * @param files the unfiltered array of files.
     * @return the filtered set of files.
     */
    public HashSet<File> sort(File[] files){
        filteredSet = filter.sort(files);
        for(File file: files){
            if(!filteredSet.contains(file))
                negatedSet.add(file);
        }
        return negatedSet;
    }
}