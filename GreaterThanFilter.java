package filesprocessing;


/**
 * GreaterThanClass that selects for files over a certain size.
 */
public class GreaterThanFilter extends SizeFilter {

    /**
     * GreaterThanFilter Constructor that takes filter's bounds.
     * @param parameters the filters bounds as a parameters list.
     */
    GreaterThanFilter(Double[] parameters){
        super(parameters);
    }

    @Override
    protected boolean attribute(double fileSize) {
        return(fileSize > firstBound);
    }
}
