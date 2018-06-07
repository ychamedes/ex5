package filesprocessing;

/**
 * PrefixFilter class that selects for files with a given prefix.
 */
public class PrefixFilter extends NameFilter {

    /**
     * PrefixFilter constructor thar receives a string in the parameters.
     * @param parameters the string parameter to compare.
     */
    PrefixFilter(String[] parameters){
        super(parameters);
    }

    @Override
    protected boolean attribute(String fileName) {
        return fileName.startsWith(namePart);
    }
}
