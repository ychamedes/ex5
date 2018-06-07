package filesprocessing;

/**
 * BetweenFilter Class that selects for files with sizes that are between two bounds.
 */
public class BetweenFilter extends SizeFilter {

    /**
     * BetweenThanFilter Constructor that takes filter's bounds.
     * @param parameters the filters bounds as a parameters list.
     */
    BetweenFilter(Double[] parameters){
        super(parameters);
    }

    @Override
    protected boolean attribute(double fileSize) {
        return(fileSize <= firstBound && fileSize >= secondBound);
    }
}
