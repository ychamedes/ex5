public class FilterFactory {

    private static final String GREATER_COMMAND = "greater_than";
    private static final String BETWEEN_COMMAND = "between";
    private static final String SMALLER_COMMAND = "smaller_than";
    private static final String FILENAME_COMMAND = "file";
    private static final String CONTAINS_COMMAND = "contains";
    private static final String PREFIX_COMMAND = "prefix";
    private static final String SUFFIX_COMMAND = "suffix";
    private static final String WRITABLE_COMMAND = "writable";
    private static final String EXECUTABLE_COMMAND = "executable";
    private static final String HIDDEN_COMMAND = "hidden";
    private static final String ALL_COMMAND = "all";
    protected static final String[] VALID_FILTERS = {GREATER_COMMAND, BETWEEN_COMMAND, SMALLER_COMMAND, FILENAME_COMMAND,
            CONTAINS_COMMAND, PREFIX_COMMAND, SUFFIX_COMMAND, WRITABLE_COMMAND, EXECUTABLE_COMMAND, HIDDEN_COMMAND,
            ALL_COMMAND};


    public static Filter buildFilter(String filterType, FilterParameter[] parameters, boolean isNot) {
        if (!isNot) {
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
                case ALL_COMMAND:
                    return new AllFilter();
                default:
                    return new AllFilter();
            }
        } else {
            switch (filterType) {
                case GREATER_COMMAND:
                    return new NotFilter(GreaterThanFilter(parameters));
                case BETWEEN_COMMAND:
                    return new NotFilter(BetweenFilter(parameters));
                case SMALLER_COMMAND:
                    return new NotFilter(SmallerThanFilter(parameters));
                case FILENAME_COMMAND:
                    return new NotFilter(FileNameFilter(parameters));
                case CONTAINS_COMMAND:
                    return new NotFilter(NameContainsFilter(parameters));
                case PREFIX_COMMAND:
                    return new NotFilter(PrefixFilter(parameters));
                case SUFFIX_COMMAND:
                    return new NotFilter(SuffixFilter(parameters));
                case WRITABLE_COMMAND:
                    return new NotFilter(WritableFilter());
                case EXECUTABLE_COMMAND:
                    return new NotFilter(ExecutableFilter());
                case HIDDEN_COMMAND:
                    return new NotFilter(HiddenFilter());
                case ALL_COMMAND:
                    return new NotFilter(AllFilter());
                default:
                    return new NotFilter(AllFilter());

            }
        }
    }
}
