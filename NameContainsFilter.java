package filesprocessing;

/**
 * NameContainsFilter class that selects for files with names containing a given sequence.
 */
public class NameContainsFilter extends NameFilter {

    /**
     * NameContainsFilter constructor thar receives a string in the parameters.
     * @param parameters the string parameter to compare.
     */
    NameContainsFilter(String[] parameters){
        super(parameters);
    }

    @Override
    protected boolean attribute(String fileName) {
        return(fileName.contains(namePart));
    }
}
