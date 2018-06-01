public class FileNameFilter extends NameFilter {

    FileNameFilter(FilterParameter...parameters){
        super(parameters);
    }

    @Override
    protected boolean attribute(String fileName) {
        return(fileName.equals(namePart));
    }
}
