package filesprocessing;

import java.io.File;

public class AllFilter extends GeneralFilter {

    @Override
    protected boolean attribute(File file) {
        return true;
    }
}