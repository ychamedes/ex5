package filesprocessing;

import java.io.File;
import java.util.Comparator;

/**
 * AbsOrder class used to order a list of files alphanumerically by absolute path.
 */
public class AbsOrder extends RegularOrder {

    /**
     * AbsOrder constructor, using the standard 'abs' comparator.
     */
    public AbsOrder() {
        this.comparator = new CompareAbs();
    }

}
