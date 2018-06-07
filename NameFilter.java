package filesprocessing;

import java.io.File;
import java.util.HashSet;

/**
 * NameFilter class, an abstract filter class that is inherited by filters to compare file names and Strings.
 */
public abstract class NameFilter implements Filter{

    /** The name to analyze. */
    String namePart;


    /**
     * NameFilter constructor, assigns the parameters to the given String with which we want to compare.
     * @param parameters list of the String parameters.
     */
    public NameFilter(String[] parameters){
        namePart = parameters[0];
    }

    /**
     * Compares the names of two files according to some attribute.
     * @param fileName the name of the file
     * @return true if the file name meets the conditions of the bounds.
     */
    protected abstract boolean attribute(String fileName);

    @Override
    public HashSet<File> sort(File[] files){
        HashSet<File> resultSet = new HashSet<>();
        for(File file : files){
            String fileName = file.getName();
            if(attribute(fileName))
                resultSet.add(file);
        }
        return resultSet;
    }

}

