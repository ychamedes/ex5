import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Parsing {

    private static final String FILTER = "FILTER";
    private static final String ORDER = "ORDER";

    public static LinkedList<Section> parseCommandsFile(String commandsFilePath){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(commandsFilePath));
            String line = reader.readLine();
            LinkedList<Section> sectionLinkedList = new LinkedList<Section>();
            boolean makingSection = false;
            int lineCount = 0;

            while (line != null) {
                lineCount++;
                if (makingSection == false){
                    if (line.equals(FILTER)) {
                        makingSection = true;
                        line = reader.readLine();
                        continue;
                    }
                } else {
                    if (line.equals(FILTER)) System.out.println("ERROR");
                    if (line.)
                }
                ) {

                    Section section = new Section(String filterCommand, String orderCommand, FilterParameter[] filterParameters, boolean filterNot,
                    boolean orderReverse, String[] errorsMessages);
                }
                System.out.println(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
