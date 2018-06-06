package filesprocessing;

public class GreaterThanFilter extends SizeFilter {

    GreaterThanFilter(FilterParameter...parameters){
        super(parameters);
    }

    @Override
    protected boolean attribute(double fileSize) {
        return(fileSize > firstBound);
    }
}
