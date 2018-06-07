package filesprocessing;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * The main class of the filesprocessing program. Coordinates the actions of all the other modules in the
 * package in order to receive and parse a list of commands and files, filter them based on a given
 * attribute, and output the file names according to a given order.
 */
public class DirectoryProcessor {

    private static final int NUMBER_VALID_ARGUMENTS = 2;

    private String sourceDirectoryPath;
    private String commandsFilePath;
    private static final String TYPE_I_ERROR_PREFIX = "Warning in line ";
    private static final String TYPE_II_ERROR_PREFIX = "ERROR: ";
    private static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments. \n";
    private static final int FILTER_LINE_CORRECTION = 2;

    /**
     * Class Constructor specifying a source directory and commands file.
     * @param sourceDirectoryPath the absolute path of the source directory
     * @param commandsFilePath the absoulute path of the commands file
     */
    public DirectoryProcessor(String sourceDirectoryPath, String commandsFilePath){
        this.sourceDirectoryPath = sourceDirectoryPath;
        this.commandsFilePath = commandsFilePath;
    }

    /**
     * Sorts the files in the source directory by using the commands in the command file.
     * Prints errors and warning for invalid commands.
     * Prints the sorted files.
     */
    public void sortDirectoryByCommands(){
        // Get list of files (and not directories) from source directory path.
        File[] unFilteredFiles = new File(sourceDirectoryPath).listFiles(pathname -> pathname.isFile());

        try {
            LinkedList<Section> sectionsList = Parsing.parseCommandsFile(commandsFilePath);

            for (Section section : sectionsList){
                // Filter the files.
                Filter filter = new AllFilter();
                try {
                    filter = FilterFactory.buildFilter(section.getFilterCommand(), section.getFilterParameters(),
                            section.getFilterNot());
                } catch (TypeIErrorException e){
                    printTypeIError(section.getLineCount() - FILTER_LINE_CORRECTION);
                }
                HashSet<File> filteredFiles = filter.sort(unFilteredFiles);

                // Order the filtered files.
                Order order = new AbsOrder();
                try {
                    order = OrderFactory.getOrder(section.getOrderCommand(), section.getOrderReverse());
                } catch (TypeIErrorException e) {
                    printTypeIError(section.getLineCount());
                }
                File[] orderedFiles = order.sort(filteredFiles);

                // Output ordered files.
                for (File file : orderedFiles){
                    System.out.println(file.getName());
                }
            }
        } catch (TypeIIErrorException e){
            printTypeIIError(e.getMessage());
        }
    }

    private static void printTypeIError(int lineCount){
        //Print a warning with given line count.
        System.err.println(TYPE_I_ERROR_PREFIX + String.valueOf(lineCount));
    }

    /**
     * Print to error a Type II Error message.
     * @param errorMessage the specific error message to print.
     */
    private static void printTypeIIError(String errorMessage){
        //Print a message involving the given parameters
        System.err.println(TYPE_II_ERROR_PREFIX + errorMessage);

        //exit system
        System.exit(0);
    }

    public static void main(String[] args){

        // Check that the number of system arguments is valid.
        if (args.length != NUMBER_VALID_ARGUMENTS){
            DirectoryProcessor.printTypeIIError(INVALID_NUMBER_OF_ARGUMENTS);
        }

        DirectoryProcessor theDirectoryProcessor = new DirectoryProcessor(args[0], args[1]);
        theDirectoryProcessor.sortDirectoryByCommands();
    }
}
