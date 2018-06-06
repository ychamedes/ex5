package filesprocessing;

public class SmallerThanFilter extends SizeFilter {

    SmallerThanFilter(FilterParameter...parameters){
        super(parameters);
    }

    @Override
    protected boolean attribute(double fileSize) {
        return(fileSize < firstBound);
    }
}
