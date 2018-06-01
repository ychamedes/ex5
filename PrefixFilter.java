public class PrefixFilter extends NameFilter {

    PrefixFilter(FilterParameter...parameters){
        super(parameters);
    }

    @Override
    protected boolean attribute(String fileName) {
        return fileName.startsWith(namePart);
    }
}
