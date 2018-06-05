import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;

public class Manager {

    private static final int NUMBER_VALID_ARGUMENTS = 2;

    private String sourceDirectoryPath;
    private String commandsFilePath;
    private static final String TYPE_II_ERROR_PREFIX = "ERROR: ";
    private static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments. \n";

    public Manager(String sourceDirectoryPath, String commandsFilePath){
        this.sourceDirectoryPath = sourceDirectoryPath;
        this.commandsFilePath = commandsFilePath;
    }

    public void sortDirectoryByCommands(){
        File[] unFilteredFiles = new File(sourceDirectoryPath).listFiles();;
        LinkedList<Section> sectionsList = Parsing.parseCommandsFile(commandsFilePath);

        for (Section section : sectionsList){
            section.printErrorMessages();

            // Filter the files.
            Filter filter = FilterFactory.buildFilter(section.filterCommand, section.filterParameters);
            HashSet<File> filteredFiles = filter.sort(unFilteredFiles);

            // Order the filtered files.
            Order order = OrderFactory.getOrder(section.orderCommand, section.orderReverse);
            File[] orderedFiles = order.sort(filteredFiles);

            // Output ordered files.
        }
    }

    protected static void printTypeIIError(String errorMessage){
        //Print a message involving the given parameters
        System.err.println(TYPE_II_ERROR_PREFIX + errorMessage);

        //exit system
        System.exit(0);
    }

    public static void main(String[] args){

        // Check that the number of system arguments is valid.
        if (args.length != NUMBER_VALID_ARGUMENTS){
            Manager.printTypeIIError(INVALID_NUMBER_OF_ARGUMENTS);
        }

        Manager theManager = new Manager(args[0], args[1]);
        theManager.sortDirectoryByCommands();
    }
}
