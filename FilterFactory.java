public class FilterFactory {

    protected static final String GREATER_COMMAND = "greater_than";
    protected static final String BETWEEN_COMMAND = "between";
    protected static final String SMALLER_COMMAND = "smaller_than";
    private static final String FILENAME_COMMAND = "file";
    private static final String CONTAINS_COMMAND = "contains";
    private static final String PREFIX_COMMAND = "prefix";
    private static final String SUFFIX_COMMAND = "suffix";
    protected static final String WRITABLE_COMMAND = "writable";
    protected static final String EXECUTABLE_COMMAND = "executable";
    protected static final String HIDDEN_COMMAND = "hidden";
    private static final String ALL_COMMAND = "all";
    protected static final String[] VALID_FILTERS = {GREATER_COMMAND, BETWEEN_COMMAND, SMALLER_COMMAND, FILENAME_COMMAND,
        CONTAINS_COMMAND, PREFIX_COMMAND, SUFFIX_COMMAND, WRITABLE_COMMAND, EXECUTABLE_COMMAND, HIDDEN_COMMAND,
        ALL_COMMAND};


    public static Filter buildFilter(String filterType, FilterParameter...parameters){
        switch (filterType) {
            case GREATER_COMMAND:
                return new GreaterThanFilter(parameters);
            case BETWEEN_COMMAND:
                return new BetweenFilter(parameters);
            case SMALLER_COMMAND:
                return new SmallerThanFilter(parameters);
            case FILENAME_COMMAND:
                return new FileNameFilter(parameters);
            case CONTAINS_COMMAND:
                return new NameContainsFilter(parameters);
            case PREFIX_COMMAND:
                return new PrefixFilter(parameters);
            case SUFFIX_COMMAND:
                return new SuffixFilter(parameters);
            case WRITABLE_COMMAND:
                return new WritableFilter();
            case EXECUTABLE_COMMAND:
                return new ExecutableFilter();
            case HIDDEN_COMMAND:
                return new HiddenFilter();
//            case ALL_COMMAND:
//                return new AllFilter();
            default:
                return new HiddenFilter();
        }
        
    }
}
