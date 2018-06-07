package filesprocessing;

public class BetweenFilter extends SizeFilter {

    BetweenFilter(Double[] parameters){
        super(parameters);
    }

    @Override
    protected boolean attribute(double fileSize) {
        return(fileSize <= firstBound && fileSize >= secondBound);
    }
}
