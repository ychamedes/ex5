package filesprocessing;

import java.io.File;


/**
 * HiddenFilter class that selects for files that are hidden.
 */
public class HiddenFilter extends GeneralFilter {

    public HiddenFilter(String attributeParam){
        super(attributeParam)
    }

    @Override
    protected boolean attribute(File file) {
        if(generalParam.equals(YES_PARAMETER))
            return(file.isHidden());
        else
            return(!file.isHidden());
    }
}
