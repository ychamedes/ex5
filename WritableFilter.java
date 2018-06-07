package filesprocessing;

import java.io.File;

/**
 * WritableFilter class that analyzes if a file is writable.
 */
public class WritableFilter extends GeneralFilter {

    @Override
    protected boolean attribute(File file) {
        return(file.canWrite());
    }
}