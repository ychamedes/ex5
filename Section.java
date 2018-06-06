package filesprocessing;

public class Section {

    private String filterCommand;
    private FilterParameter[] filterParameters;
    private String orderCommand;
    private boolean filterNot;
    private boolean orderReverse;
    private int lineCount;

    Section(String filterCommand, String orderCommand, FilterParameter[] filterParameters, boolean filterNot,
            boolean orderReverse, int lineCount){
        this.filterCommand = filterCommand;
        this.filterParameters = filterParameters;
        this.orderCommand = orderCommand;
        this.filterNot = filterNot;
        this.orderReverse = orderReverse;
        this.lineCount = lineCount;
    }

    protected String getFilterCommand(){
        return filterCommand;
    }

    protected String getOrderCommand(){
        return orderCommand;
    }

    protected FilterParameter[] getFilterParameters(){
        return filterParameters;
    }

    protected boolean getFilterNot(){
        return filterNot;
    }

    protected boolean getOrderReverse(){
        return orderReverse;
    }

    protected int getLineCount(){
        return lineCount;
    }

}