import java.io.File;

public class ExecutableFilter extends AttributeFilter {

    @Override
    protected boolean attribute(File file) {
        return(file.canExecute());
    }
}
