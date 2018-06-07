package filesprocessing;

import java.io.File;
import java.util.HashSet;

/**
 * SizeFilter class, an abstract filter class that is inherited by filters to compare file size.
 */
public abstract class SizeFilter implements Filter{

    private static final int MAX_PARAMETERS = 2;
    private static final int K_BYTE_CONVERSION_FACTOR = 1024;

    /** The first bound of the size parameter. */
    double firstBound;

    /** The second bound of the size parameter. */
    double secondBound;

    /**
     * SizeFilter constructor, assigns the parameters to the first and (if exist) second bounds.
     * @param parameters list of the bounds parameters.
     */
    public SizeFilter(Double[] parameters){
        firstBound = parameters[0];
        if(parameters.length == MAX_PARAMETERS)
            secondBound = parameters[1];
    }

    /**
     * Compares the size of two files according to some attribute.
     * @param fileSize the size of the file, converted from bytes.
     * @return true if the file size meets the conditions of the bounds.
     */
    protected abstract boolean attribute(double fileSize);

    @Override
    public HashSet<File> sort(File[] files){
        HashSet<File> resultSet = new HashSet<>();
        for(File file : files){
            double fileSize = (file.getTotalSpace())/ K_BYTE_CONVERSION_FACTOR;
            if(attribute(fileSize))
                resultSet.add(file);
        }
        return resultSet;
    }

}
