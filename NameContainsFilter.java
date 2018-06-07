package filesprocessing;

public class NameContainsFilter extends NameFilter {

    NameContainsFilter(String[] parameters){
        super(parameters);
    }

    @Override
    protected boolean attribute(String fileName) {
        return(fileName.contains(namePart));
    }
}
