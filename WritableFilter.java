package filesprocessing;

import java.io.File;

/**
 * WritableFilter class that selects for files that are writable.
 */
public class WritableFilter extends GeneralFilter {

    public WritableFilter(String attributeParam){
        super(attributeParam)
    }

    @Override
    protected boolean attribute(File file) {
        if(generalParam.equals(YES_PARAMETER))
            return(file.canWrite());
        else
            return(!file.canWrite());
    }
}
