package filesprocessing;

/**
 * SmallerThanClass that analyzes a file's size as less than a bound.
 */
public class SmallerThanFilter extends SizeFilter {
    /**
     * SmallerThanFilter Constructor that takes filter's bounds.
     * @param parameters the filters bounds as a parameters list.
     */
    SmallerThanFilter(Double[] parameters){
        super(parameters);
    }

    @Override
    protected boolean attribute(double fileSize) {
        return(fileSize < firstBound);
    }
}
