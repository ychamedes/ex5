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
    private static final String YES_PARAMETER = "YES";
    private static final String NO_PARAMETER = "NO";


    public static Filter buildFilter(String filterType, FilterParameter[] parameters, boolean isNot) throws
            TypeIErrorException{

        if (!isNot) {
            switch (filterType) {
                case GREATER_COMMAND:
                    checkSizeParams(parameters);
                    return new GreaterThanFilter(parameters);
                case BETWEEN_COMMAND:
                    checkSizeParams(parameters);
                    return new BetweenFilter(parameters);
                case SMALLER_COMMAND:
                    checkSizeParams(parameters);
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
                    checkAttributeParams(parameters);
                    return new WritableFilter();
                case EXECUTABLE_COMMAND:
                    checkAttributeParams(parameters);
                    return new ExecutableFilter();
                case HIDDEN_COMMAND:
                    checkAttributeParams(parameters);
                    return new HiddenFilter();
                case ALL_COMMAND:
                    return new AllFilter();
                default:
                    throw new TypeIErrorException();
            }
        } else {
            switch (filterType) {
                case GREATER_COMMAND:
                    checkSizeParams(parameters);
                    return new NotFilter(new GreaterThanFilter(parameters));
                case BETWEEN_COMMAND:
                    checkSizeParams(parameters);
                    return new NotFilter(new BetweenFilter(parameters));
                case SMALLER_COMMAND:
                    checkSizeParams(parameters);
                    return new NotFilter(new SmallerThanFilter(parameters));
                case FILENAME_COMMAND:
                    return new NotFilter(new FileNameFilter(parameters));
                case CONTAINS_COMMAND:
                    return new NotFilter(new NameContainsFilter(parameters));
                case PREFIX_COMMAND:
                    return new NotFilter(new PrefixFilter(parameters));
                case SUFFIX_COMMAND:
                    return new NotFilter(new SuffixFilter(parameters));
                case WRITABLE_COMMAND:
                    checkAttributeParams(parameters);
                    return new NotFilter(new WritableFilter());
                case EXECUTABLE_COMMAND:
                    checkAttributeParams(parameters);
                    return new NotFilter(new ExecutableFilter());
                case HIDDEN_COMMAND:
                    checkAttributeParams(parameters);
                    return new NotFilter(new HiddenFilter());
                case ALL_COMMAND:
                    return new NotFilter(new AllFilter());
                default:
                    throw new TypeIErrorException();
            }
        }
    }

    private static void checkSizeParams(FilterParameter[] parameters) throws TypeIErrorException{
        double firstParam = parameters[0].getIntParam();
        double secondParam = firstParam;
        if(parameters.length > 1) secondParam = parameters[1].getIntParam();
        if((firstParam < 0) || (secondParam < 0) || (secondParam < firstParam))
            throw new TypeIErrorException();
    }

    private static void checkAttributeParams(FilterParameter[] parameters) throws TypeIErrorException{
        String param = parameters[0].getStringParam();
        if (!param.equals(YES_PARAMETER) && !param.equals(NO_PARAMETER))
            throw new TypeIErrorException();
    }
}
