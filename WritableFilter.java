package filesprocessing;

import java.io.File;

public class WritableFilter extends GeneralFilter {

    @Override
    protected boolean attribute(File file) {
        return(file.canWrite());
    }
}