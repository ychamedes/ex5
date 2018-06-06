package filesprocessing;

import java.io.File;
import java.util.HashSet;

public abstract class NameFilter implements Filter{

    String namePart;

    public NameFilter(FilterParameter...parameters){
        namePart = parameters[0].getStringParam();
    }

    protected abstract boolean attribute(String fileName);

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

