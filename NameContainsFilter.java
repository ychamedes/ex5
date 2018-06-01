public class NameContainsFilter extends NameFilter {

    NameContainsFilter(FilterParameter...parameters){
        super(parameters);
    }

    @Override
    protected boolean attribute(String fileName) {
        return(fileName.contains(namePart));
    }
}
