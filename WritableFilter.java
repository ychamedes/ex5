import java.io.File;

public class WritableFilter extends AttributeFilter {

    @Override
    protected boolean attribute(File file) {
        return(file.canWrite());
    }
}
