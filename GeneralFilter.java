import java.io.File;
import java.util.HashSet;

public abstract class GeneralFilter implements Filter{

    protected abstract boolean attribute(java.io.File file);

    public HashSet<File> sort(File[] files){
        HashSet<File> resultSet = new HashSet<>();
        for(File file : files){
            if(attribute(file))
                resultSet.add(file);
        }
        return resultSet;
    }

}

