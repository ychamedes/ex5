package filesprocessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
    private static final String PARAMETER_SPLIT = "#";

    public static LinkedList<Section> parseCommandsFile(String commandsFilePath)  throws TypeIIErrorException {
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
                            orderCommand = null;
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
        FilterParameter[] finalParametersList = new FilterParameter[paramLength];

        // Starting with the second string of parameters (the first is the filter type), compose a list of parameters.
        for(int paramCounter = 1; paramCounter < paramLength; paramCounter++){
            finalParametersList[paramCounter - 1] = new FilterParameter(splitFilter[paramCounter]);
        }

        return new Section(filterType, orderType, finalParametersList, filterNegate, orderNegate, lineCount);
    }
}