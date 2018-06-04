public class Section {
    

    String[] errorMessages;
    String filterCommand;
    FilterParameter[] filterParameters;
    String orderCommand;
    boolean filterNot;
    boolean orderReverse;

    Section(String filterCommand, String orderCommand, FilterParameter[] filterParameters, boolean filterNot, 
            boolean orderReverse, String[] errorsMessages){
        this.errorMessages = errorsMessages;
        this.filterCommand = filterCommand;
        this.filterParameters = filterParameters;
        this.orderCommand = orderCommand;
        this.filterNot = filterNot;
        this.orderReverse = orderReverse;
    }

    void printErrorMessages(){
        for(String message: errorMessages){
            if(message != null) System.out.println(message);
        }
    }
}
