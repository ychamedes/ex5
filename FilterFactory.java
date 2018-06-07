package filesprocessing;

/**
 * An FilterFactory that creates an instance of the desired filter class.
 */
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

    /**
     * Returns the desired type of filter, either a regular one or a "NOT" one.
     * @param filterType type of filter to be returned.
     * @param parameters parameters of the filter.
     * @param isNot should the filter be "NOT".
     * @return instance of the desired filter.
     * @throws TypeIErrorException throw an error if the filter type or parameters are invalid.
     */
    public static Filter buildFilter(String filterType, String[] parameters, boolean isNot) throws
            TypeIErrorException{
        Double[] newParams;
        if (!isNot) {
            switch (filterType) {
                case GREATER_COMMAND:
                    newParams = checkSizeParams(parameters, filterType);
                    return new GreaterThanFilter(newParams);
                case BETWEEN_COMMAND:
                    newParams = checkSizeParams(parameters, filterType);
                    return new BetweenFilter(newParams);
                case SMALLER_COMMAND:
                    newParams = checkSizeParams(parameters, filterType);
                    return new SmallerThanFilter(newParams);
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
            /* Use NotFilter decorator. */
            switch (filterType) {
                case GREATER_COMMAND:
                    newParams = checkSizeParams(parameters, filterType);
                    return new NotFilter(new GreaterThanFilter(newParams));
                case BETWEEN_COMMAND:
                    newParams = checkSizeParams(parameters, filterType);
                    return new NotFilter(new BetweenFilter(newParams));
                case SMALLER_COMMAND:
                    newParams = checkSizeParams(parameters, filterType);
                    return new NotFilter(new SmallerThanFilter(newParams));
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

    /**
     * Checks the validity of the parameters given to the GreaterThan, SmallerThan, and Between filters
     * @param parameters the bound/s used in the filter
     * @param filterType a String representing the name of the intended filter
     * @return An array of doubles representing the bound/s to be used in the filter
     * @throws TypeIErrorException throws an error if a parameter is illegal
     */
    private static Double[] checkSizeParams(String[] parameters, String filterType) throws TypeIErrorException{
        for(String param : parameters) {
            // Check parameters are numbers.
            if (param != null && !(param.matches("\\d+(\\.\\d+)?"))) {
                throw new TypeIErrorException();
            }
        }

        double firstParam = Double.parseDouble(parameters[0]);
        double secondParam = firstParam;
        if(parameters.length > 1){
            //Check invalid number of parameters
            if (filterType.equals(SMALLER_COMMAND) || filterType.equals(GREATER_COMMAND)) {
                throw new TypeIErrorException();
            } else {
                secondParam = Double.parseDouble(parameters[1]);
            }
        }
        //Check for invalid number parameters
        if((firstParam < 0) || (secondParam < 0) || (secondParam < firstParam)){
            throw new TypeIErrorException();
        }
        return new Double[]{firstParam, secondParam};
    }

    /**
     * Checks the validity of the parameters of an attribute filter.
     * @param parameters the parameters to check.
     * @throws TypeIErrorException throws an error if the parameters are invalid.
     */
    private static void checkAttributeParams(String[] parameters) throws TypeIErrorException{
        String param = parameters[0];
        if (!param.equals(YES_PARAMETER) && !param.equals(NO_PARAMETER))
            throw new TypeIErrorException();
    }
}
