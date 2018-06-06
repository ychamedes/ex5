package filesprocessing;

import java.io.File;

public class HiddenFilter extends GeneralFilter {

    @Override
    protected boolean attribute(File file) {
        return (file.isHidden());
    }
}