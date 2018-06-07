package filesprocessing;

import java.io.File;
import java.util.HashSet;

public abstract class NameFilter implements Filter{

    /** The name to analyze. */
    String namePart;

    public NameFilter(String[] parameters){
        namePart = parameters[0];
    }

    /**
     *
     * @param fileName
     * @return
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

