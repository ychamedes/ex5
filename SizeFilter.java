import java.io.File;
import java.util.HashSet;

public abstract class SizeFilter implements Filter{

    private static final int MAX_PARAMETERS = 2;
    private static final int K_BYTE_CONVERSION_FACTOR = 1024;

    protected int firstBound;
    protected int secondBound;

    public SizeFilter(FilterParameter...parameters){
        firstBound = parameters[0].getIntParam();
        if(parameters.length == MAX_PARAMETERS)
            secondBound = parameters[1].getIntParam();
    }

    protected abstract boolean attribute(double fileSize);

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
