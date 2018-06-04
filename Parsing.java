import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Parsing {

    private static final String FILTER = "FILTER";
    private static final String ORDER = "ORDER";
    private static final String ERROR = "ERROR: ";
    private static final String NOT = "NOT";
    private static final String REVERSE = "REVERSE";
    private static final String FILTER_BAD_SUBSECTION_NAME = "Filter sub-section name may be misspelled. \n";
    private static final String ORDER_BAD_SUBSECTION_NAME = "Order sub-section name may be misspelled. \n";
    private static final String FILTER_SUBSECTION_MISSING = "Filter sub-section is missing. \n";
    private static final String ORDER_SUBSECTION_MISSING = "Order sub-section is missing. \n";
    private static final int MAXIMUM_ERROR_WARNINGS = 2;
    private static final String PARAMETER_SPLIT = "#";


    public static LinkedList<Section> parseCommandsFile(String commandsFilePath) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(commandsFilePath));
            LinkedList<Section> sectionLinkedList = new LinkedList<Section>();
            String line = reader.readLine();
            boolean firstSubsection = true;
            String filterCommand;
            String orderCommand;
            int lineCount = 0;

            while (line != null) {
                lineCount++;
                if (firstSubsection) {
                    if (line.equals(FILTER)) {
                        filterCommand = reader.readLine();
                        lineCount++;
                        firstSubsection = false;
                        line = reader.readLine();
                    } else {
                        if (line.equals(ORDER))
                            printTypeIIError(FILTER_SUBSECTION_MISSING);
                        else printTypeIIError(FILTER_BAD_SUBSECTION_NAME);
                    }
                } else {
                    if (line.equals(ORDER)) {
                        orderCommand = reader.readLine();
                        lineCount++;
                        if (orderCommand != null && !orderCommand.equals(FILTER)) {
                            line = reader.readLine();
                        }
                        else{
                            line = orderCommand;
                            orderCommand = null;
                        }
                        firstSubsection = true;
                        sectionLinkedList.add(buildSection(filterCommand, orderCommand, lineCount));
                    } else {
                        if (line.equals(FILTER))
                            printTypeIIError(ORDER_SUBSECTION_MISSING);
                        else printTypeIIError(ORDER_BAD_SUBSECTION_NAME);
                    }
                }
            }
            return sectionLinkedList;
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Section buildSection(String filterCommand, String orderCommand, int lineCount){
        if(orderCommand == null){
            orderCommand = DEFAULT_ORDER;
        }
        String[] errorWarnings = new String[MAXIMUM_ERROR_WARNINGS];
        String[] splitFilter = filterCommand.split(PARAMETER_SPLIT);
        String[] splitOrder = orderCommand.split(PARAMETER_SPLIT);

        //Parse filter and order types
        String filterType = splitFilter[0];
        //check validity
        String orderType = splitOrder[0];
        //check validity

        //Parse NOT and REVERSE operators

        boolean filterNegate = false;
        boolean orderNegate = false;

        if(splitFilter[splitFilter.length - 1].equals(NOT))
            filterNegate = true;
        if(splitOrder[splitOrder.length - 1].equals(REVERSE))
            orderNegate = true;

        //Parse filter parameters
        int paramLength = splitFilter.length;
        if(filterNegate) paramLength--;
        FilterParameter[] parameters = new FilterParameter[paramLength];
        for(int paramCounter = 1; paramCounter < paramLength; paramCounter++){
            parameters[paramCounter - 1] = new FilterParameter(splitFilter[paramCounter]);
        }
        //Check validities


        return new Section(filterType, orderType, parameters, filterNegate, orderNegate, errorWarnings);
    }




    public static void printTypeIIError(String errorMessage){
        //Print a message involving the given parameters
        System.err.println(ERROR + errorMessage);

        //exit system
        System.exit(0);
    }
}
