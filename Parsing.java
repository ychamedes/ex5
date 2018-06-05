import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Parsing {

    private static final String FILTER = "FILTER";
    private static final String ORDER = "ORDER";
    private static final String NOT = "NOT";
    private static final String REVERSE = "REVERSE";
    private static final String FILTER_BAD_SUBSECTION_NAME = "Filter sub-section name may be misspelled. \n";
    private static final String ORDER_BAD_SUBSECTION_NAME = "Order sub-section name may be misspelled. \n";
    private static final String FILTER_SUBSECTION_MISSING = "Filter sub-section is missing. \n";
    private static final String ORDER_SUBSECTION_MISSING = "Order sub-section is missing. \n";
    private static final String IO_ERROR = "IO error encountered. \n";
    private static final String TYPE_I_ERROR_PREFIX = "Warning in line ";
    private static final int MAXIMUM_ERROR_WARNINGS = 2;
    private static final int FILTER_ERROR_WARNING = 0;
    private static final int ORDER_ERROR_WARNING = 1;
    private static final String PARAMETER_SPLIT = "#";

    public static LinkedList<Section> parseCommandsFile(String commandsFilePath) {
        BufferedReader reader;
        LinkedList<Section> sectionLinkedList = new LinkedList<>();

        try {
            reader = new BufferedReader(new FileReader(commandsFilePath));
            String line = reader.readLine();
            boolean firstSubsection = true;
            String filterCommand = null;
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
                        if (line.equals(ORDER)) Manager.printTypeIIError(FILTER_SUBSECTION_MISSING);
                        else Manager.printTypeIIError(FILTER_BAD_SUBSECTION_NAME);
                    }
                } else {
                    if (line.equals(ORDER)) {
                        orderCommand = reader.readLine();
                        if (orderCommand != null && !orderCommand.equals(FILTER)) {
                            lineCount++;
                            line = reader.readLine();
                        }
                        else{
                            line = orderCommand;
                            orderCommand = null;
                        }
                        firstSubsection = true;
                        sectionLinkedList.add(buildSection(filterCommand, orderCommand, lineCount));
                    } else {
                        if (line.equals(FILTER)) Manager.printTypeIIError(ORDER_SUBSECTION_MISSING);
                        else Manager.printTypeIIError(ORDER_BAD_SUBSECTION_NAME);
                    }
                }
            }
            reader.close();
        }
        catch (IOException ioError) {
            Manager.printTypeIIError(IO_ERROR);
        }

        return sectionLinkedList;
    }

    public static Section buildSection(String filterCommand, String orderCommand, int lineCount){
        String[] errorWarnings = new String[MAXIMUM_ERROR_WARNINGS];
        String[] splitFilter = filterCommand.split(PARAMETER_SPLIT);
        String[] splitOrder = orderCommand.split(PARAMETER_SPLIT);

        //Parse filter and order types
        String filterType = splitFilter[0];
        //Check validity of filter type.
        if (!Arrays.asList(FilterFactory.VALID_FILTERS).contains(filterType)){
            errorWarnings[FILTER_ERROR_WARNING] = TYPE_I_ERROR_PREFIX + String.valueOf(lineCount - 2);
        }
        String orderType = splitOrder[0];
        //Check validity of order type.
        if (orderType != null && !Arrays.asList(OrderFactory.VALID_ORDERS).contains(orderType)){
            errorWarnings[ORDER_ERROR_WARNING] = TYPE_I_ERROR_PREFIX + String.valueOf(lineCount);
        }
        //Check validity of size filter parameters.
        if (filterType.equals(FilterFactory.GREATER_COMMAND) || filterType.equals(FilterFactory.SMALLER_COMMAND)
                || filterType.equals(FilterFactory.BETWEEN_COMMAND)){
            if (Integer.valueOf(splitFilter[0]) < 0){
                errorWarnings[FILTER_ERROR_WARNING] = TYPE_I_ERROR_PREFIX + String.valueOf(lineCount - 2);
            }
            //Check validity of between second parameter.
            if (filterType.equals(FilterFactory.BETWEEN_COMMAND) &&
                    Integer.valueOf(splitFilter[0]) > Integer.valueOf(splitFilter[1])){
                errorWarnings[FILTER_ERROR_WARNING] = TYPE_I_ERROR_PREFIX + String.valueOf(lineCount - 2);
            }
        }
        //Check validity of executable filter parameters.
        if (filterType.equals(FilterFactory.EXECUTABLE_COMMAND) || filterType.equals(FilterFactory.HIDDEN_COMMAND)
                || filterType.equals(FilterFactory.WRITABLE_COMMAND)){
            if (!(splitFilter[0].equals("YES") || splitFilter[0].equals("NO"))){
                errorWarnings[FILTER_ERROR_WARNING] = TYPE_I_ERROR_PREFIX + String.valueOf(lineCount - 2);
            }
        }

        //Parse NOT and REVERSE operators
        boolean filterNegate = false;
        boolean orderNegate = false;

        if(splitFilter[splitFilter.length - 1].equals(NOT)) filterNegate = true;
        if(splitOrder[splitOrder.length - 1].equals(REVERSE)) orderNegate = true;

        //Parse filter parameters
        int paramLength = splitFilter.length;
        if(filterNegate) paramLength--;
        FilterParameter[] finalParametersList = new FilterParameter[paramLength];

        // Starting with the second string of parameters (the first is the filter type), compose a list of parameters.
        for(int paramCounter = 1; paramCounter < paramLength; paramCounter++){
            finalParametersList[paramCounter - 1] = new FilterParameter(splitFilter[paramCounter]);
        }

        return new Section(filterType, orderType, finalParametersList, filterNegate, orderNegate, errorWarnings);
    }
}
