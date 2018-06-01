public class BetweenFilter extends SizeFilter {

    BetweenFilter(FilterParameter...parameters){
        super(parameters);
    }

    @Override
    protected boolean attribute(double fileSize) {
        return(fileSize <= firstBound && fileSize >= secondBound);
    }
}
