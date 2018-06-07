package filesprocessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * A module that parses a file containing a list of commands for the filesprocessing program. Builds
 * Section objects to be passed back to the directoryProcessor class, which are then interpreted as
 * commands for filters and orders.
 */
public class Parsing {

    private static final String FILTER = "FILTER";
    private static final String ORDER = "ORDER";
    private static final String NOT = "NOT";
    private static final String REVERSE = "REVERSE";
    private static final String ORDER_DEFAULT = "abs";
    private static final String FILTER_BAD_SUBSECTION_NAME = "Filter sub-section name may be misspelled. \n";
    private static final String ORDER_BAD_SUBSECTION_NAME = "Order sub-section name may be misspelled. \n";
    private static final String FILTER_SUBSECTION_MISSING = "Filter sub-section is missing. \n";
    private static final String ORDER_SUBSECTION_MISSING = "Order sub-section is missing. \n";
    private static final String IO_ERROR = "IO error encountered. \n";
    private static final String PARAMETER_SPLIT = "#";

    /**
     * Take a String representing the path of a file containing a list of commands. Use the list of
     * commands to build and return a LinkedList of Section objects
     * @param commandsFilePath
     * @return A LinkedList of Section objects representing the commands to be carried out
     * @throws TypeIIErrorException if there is a Type II error in the commands file
     */
    public static LinkedList<Section> parseCommandsFile(String commandsFilePath)  throws TypeIIErrorException {
        BufferedReader reader;
        LinkedList<Section> sectionLinkedList = new LinkedList<>();

        try {
            // Reads the provided commands file using buffering
            reader = new BufferedReader(new FileReader(commandsFilePath));
            String line = reader.readLine();
            boolean firstSubsection = true;
            String filterCommand = null;
            String orderCommand;
            int lineCount = 0;

            // Passes through each line of the file, interpreting it and building sections according to
            // those instructions
            while (line != null) {
                lineCount++;
                if (firstSubsection) {
                    if (line.equals(FILTER)) {
                        filterCommand = reader.readLine();
                        if(filterCommand == null) {
                            throw new TypeIIErrorException(ORDER_SUBSECTION_MISSING);
                        }
                        lineCount++;
                        firstSubsection = false;
                        line = reader.readLine();
                    } else {
                        if (line.equals(ORDER)) throw new TypeIIErrorException(FILTER_SUBSECTION_MISSING);
                        else throw new TypeIIErrorException(FILTER_BAD_SUBSECTION_NAME);
                    }
                } else {
                    int finalLineCount;
                    if (line.equals(ORDER)) {
                        orderCommand = reader.readLine();
                        if (orderCommand != null && !orderCommand.equals(FILTER)) {
                            lineCount++;
                            line = reader.readLine();
                            finalLineCount = lineCount;
                        }
                        else{
                            line = orderCommand;
                            orderCommand = ORDER_DEFAULT;
                            //Regardless of order commanding existing, build section with same line count.
                            finalLineCount = lineCount + 1;
                        }
                        firstSubsection = true;
                        sectionLinkedList.add(buildSection(filterCommand, orderCommand, finalLineCount));
                    } else {
                        if (line.equals(FILTER)) throw new TypeIIErrorException(ORDER_SUBSECTION_MISSING);
                        else throw new TypeIIErrorException(ORDER_BAD_SUBSECTION_NAME);
                    }
                }
            }
            reader.close();
        }
        catch (IOException ioError) {
            throw new TypeIIErrorException(IO_ERROR);
        }

        return sectionLinkedList;
    }

    /**
     * Constructs a Section object representing the commands given in a specific section of the commands file
     * @param filterCommand the line in the commands file representing the desired filter and parameters
     * @param orderCommand the line in the commands file representing the desired order and parameters
     * @param lineCount the number of the current line in the commands file
     * @return A Section object containing the information to be used by directory processor
     */
    public static Section buildSection(String filterCommand, String orderCommand, int lineCount){
        String[] splitFilter = filterCommand.split(PARAMETER_SPLIT);
        String[] splitOrder = orderCommand.split(PARAMETER_SPLIT);

        //Parse filter and order types
        String filterType = splitFilter[0];

        //Check validity of filter type.
        String orderType = splitOrder[0];

        //Parse NOT and REVERSE operators
        boolean filterNegate = false;
        boolean orderNegate = false;

        if(splitFilter[splitFilter.length - 1].equals(NOT)) filterNegate = true;
        if(splitOrder[splitOrder.length - 1].equals(REVERSE)) orderNegate = true;

        //Parse filter parameters
        int paramLength = splitFilter.length;
        if(filterNegate) paramLength--;
        String[] finalParametersList = new String[paramLength];

        // Starting with the second string of parameters (the first is the filter type), compose a list of parameters.
        System.arraycopy(splitFilter, 1, finalParametersList, 0, paramLength - 1);

        return new Section(filterType, orderType, finalParametersList, filterNegate, orderNegate, lineCount);
    }
}
