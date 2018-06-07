package filesprocessing;

public class FileNameFilter extends NameFilter {

    FileNameFilter(String[] parameters){
        super(parameters);
    }

    @Override
    protected boolean attribute(String fileName) {
        return(fileName.equals(namePart));
    }
}
