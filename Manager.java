import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;

public class Manager {

    private static final int NUMBER_VALID_ARGUMENTS = 2;

    private String sourceDirectoryPath;
    private String commandsFilePath;

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

    public static void main(String[] args){

        // Check that the number of system arguments is valid.
        if (args.length != NUMBER_VALID_ARGUMENTS){
            System.err.println("ERROR: Invalid number of arguments. \n");
            return;
        }

        Manager theManager = new Manager(args[0], args[1]);
        theManager.sortDirectoryByCommands();
    }
}
