package filesprocessing;

import java.io.File;

/**
 * AllFilter class that "selects" for all files.
 */
public class AllFilter extends GeneralFilter {

    @Override
    protected boolean attribute(File file) {
        return true;
    }
}
