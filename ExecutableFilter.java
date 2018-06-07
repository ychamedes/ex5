package filesprocessing;

import java.io.File;


/**
 * ExecutableFilter class that selects for files that are executable.
 */
public class ExecutableFilter extends GeneralFilter {


    public ExecutableFilter(String attributeParam){
        super(attributeParam)
    }

    @Override
    protected boolean attribute(File file) {
        if(generalParam.equals(YES_PARAMETER))
            return(file.canExecute());
        else
            return(!file.canExecute());
    }
}
