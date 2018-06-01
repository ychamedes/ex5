public class FilterFactory {

    private static final String GREATER_COMMAND = "greater_than";
    private static final String BETWEEN_COMMAND = "between";
    private static final String SMALLER_COMMAND = "smaller_than";
    private static final String FILENAME_COMMAND = "file";
    private static final String CONTAINS_COMMAND = "contains";
    private static final String PREFIX_COMMAND = "prefix";
    private static final String SUFFIX_COMMAND = "sufffix";
    private static final String WRITABLE_COMMAND = "writable";
    private static final String EXECUTABLE_COMMAND = "executable";
    private static final String HIDDEN_COMMAND = "hidden";
    private static final String ALL_COMMAND = "all";


    public static Filter buildFilter(String filterType, FilterParameter...parameters){
        if(filterType.equals(GREATER_COMMAND)) return new GreaterThanFilter(parameters);
        else if(filterType.equals(BETWEEN_COMMAND)) return new BetweenFilter(parameters);
        else if(filterType.equals(SMALLER_COMMAND)) return new SmallerThanFilter(parameters);
        else if(filterType.equals(FILENAME_COMMAND)) return new FileNameFilter(parameters);
        else if(filterType.equals(CONTAINS_COMMAND)) return new NameContainsFilter(parameters);
        else if(filterType.equals(PREFIX_COMMAND)) return new PrefixFilter(parameters);
        else if(filterType.equals(SUFFIX_COMMAND)) return new SuffixFilter(parameters);
        else if(filterType.equals(WRITABLE_COMMAND)) return new WritableFilter();
        else if(filterType.equals(EXECUTABLE_COMMAND)) return new ExecutableFilter();
        else if(filterType.equals(HIDDEN_COMMAND)) return new HiddenFilter();
        //if(filterType.equals(ALL_COMMAND)) return new AllFilter();
        else return new HiddenFilter();
        
    }
}
