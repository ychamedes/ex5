public class SuffixFilter extends NameFilter {

    SuffixFilter(FilterParameter...parameters){
        super(parameters);
    }

    @Override
    protected boolean attribute(String fileName) {
        return fileName.endsWith(namePart);
    }
}
