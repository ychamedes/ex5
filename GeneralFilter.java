package filesprocessing;

import java.io.File;
import java.util.HashSet;

/**
 * GeneralFilter class, an abstract filter class that is inherited by filters that compare attributes, or
 * other general purpose filters that may be added to the module.
 */

public abstract class GeneralFilter implements Filter{

    protected static final String YES_PARAMETER = "YES";
    protected static final String NO_PARAMETER = "NO";

    /** A field to store a parameter for the filter to compare to. */
    protected String generalParam;

    //public GeneralFilter(){}

    public GeneralFilter(String generalParameter){
        this.generalParam = generalParameter
    }

    /**
     * Judges the file based on some attribute.
     * @param file the file to be analyzed
     * @return true if the file name meets the condition.
     */
    protected abstract boolean attribute(java.io.File file);

    @Override
    public HashSet<File> sort(File[] files){
        HashSet<File> resultSet = new HashSet<>();
        for(File file : files){
            if(attribute(file))
                resultSet.add(file);
        }
        return resultSet;
    }

}
