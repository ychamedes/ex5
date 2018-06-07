package filesprocessing;

public class PrefixFilter extends NameFilter {

    PrefixFilter(String[] parameters){
        super(parameters);
    }

    @Override
    protected boolean attribute(String fileName) {
        return fileName.startsWith(namePart);
    }
}
