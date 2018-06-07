package filesprocessing;

/**
 * SmallerThanClass that selects for files under a certain size.
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
