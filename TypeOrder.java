package filesprocessing;

import java.io.File;
import java.util.Comparator;

/**
 * TypeOrder class used to order a list of files by type.
 */
public class TypeOrder extends RegularOrder {

    /**
     * TypeOrder Constructor.
     */
    TypeOrder() {
        this.comparator = new CompareType();
    }

    /**
     * A local Comparator class that compares the types of two files.
     * File names with a "." at the end are considered to have no type.
     */
    private class CompareType implements Comparator<File> {

        private final String EXTENSION_INDICATOR = ".";
        private final String NO_EXTENSION = "";

        @Override
        public int compare(File o1, File o2) {
            String o1Type = NO_EXTENSION;
            String o2Type = NO_EXTENSION;

            if (o1.getName().contains(EXTENSION_INDICATOR)){
                o1Type = o1.getName().substring(o1.getName().lastIndexOf(EXTENSION_INDICATOR));
                if (o1Type.equals(EXTENSION_INDICATOR)) o1Type = NO_EXTENSION;
            }
            if (o2.getName().contains(EXTENSION_INDICATOR)){
                o2Type = o2.getName().substring(o2.getName().lastIndexOf(EXTENSION_INDICATOR));
                if (o2Type.equals(EXTENSION_INDICATOR)) o2Type = NO_EXTENSION;
            }

            return o1Type.compareTo(o2Type);
        }
    }
}
