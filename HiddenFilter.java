import java.io.File;

public class HiddenFilter extends AttributeFilter {

    @Override
    protected boolean attribute(File file) {
        return (file.isHidden());
    }
}
