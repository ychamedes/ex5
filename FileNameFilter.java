package filesprocessing;

/**
 * FileNameFilter class that selects for files that match a given name.
 */
public class FileNameFilter extends NameFilter {

    /**
     * FileNameFilter constructor thar receives a string in the parameters.
     * @param parameters the string parameter to compare.
     */
    FileNameFilter(String[] parameters){
        super(parameters);
    }

    @Override
    protected boolean attribute(String fileName) {
        return(fileName.equals(namePart));
    }
}
