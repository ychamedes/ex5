package filesprocessing;

/**
 * Section class that represents a section in the command file and its parameters.
 */
public class Section {

    private String filterCommand;
    private String[] filterParameters;
    private String orderCommand;
    private boolean filterNot;
    private boolean orderReverse;
    private int lineCount;

    /**
     * Section Constructor that takes the parameters of the section.
     * @param filterCommand the type of filter
     * @param orderCommand the type of order
     * @param filterParameters the parameters of the filter
     * @param filterNot is the filter "NOT"
     * @param orderReverse is the order "REVERSE"
     * @param lineCount the final line count of the section in the original file
     */
    Section(String filterCommand, String orderCommand, String[] filterParameters, boolean filterNot,
            boolean orderReverse, int lineCount){
        this.filterCommand = filterCommand;
        this.filterParameters = filterParameters;
        this.orderCommand = orderCommand;
        this.filterNot = filterNot;
        this.orderReverse = orderReverse;
        this.lineCount = lineCount;
    }

    /**
     * Return the filter command.
     * @return the filter command.
     */
    protected String getFilterCommand(){
        return filterCommand;
    }

    /**
     * Return the order command.
     * @return the order command.
     */
    protected String getOrderCommand(){
        return orderCommand;
    }

    /**
     * Return an array of the parameters.
     * @return the parameters.
     */
    protected String[] getFilterParameters(){
        return filterParameters;
    }

    /**
     * Return if the filter is "NOT"
     * @return true if the filter is "NOT"
     */
    protected boolean getFilterNot(){
        return filterNot;
    }

    /**
     * Return if the filter is "REVERSE"
     * @return true if the filter is "REVERSE"
     */
    protected boolean getOrderReverse(){
        return orderReverse;
    }

    /**
     * Return the line count of the section.
     * @return the line count of the section.
     */
    protected int getLineCount(){
        return lineCount;
    }

}