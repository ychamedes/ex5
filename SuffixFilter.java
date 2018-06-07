package filesprocessing;

/**
 * SuffixFilter class that analyzes if a file name ends with a specific string.
 */
public class SuffixFilter extends NameFilter {

    /**
     * SuffixFilter constructor thar receives a string in the parameters.
     * @param parameters the string parameter to compare.
     */
    SuffixFilter(String[] parameters){
        super(parameters);
    }

    @Override
    protected boolean attribute(String fileName) {
        return fileName.endsWith(namePart);
    }
}
