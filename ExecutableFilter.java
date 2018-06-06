import java.io.File;

public class ExecutableFilter extends GeneralFilter {

    @Override
    protected boolean attribute(File file) {
        return(file.canExecute());
    }
}
