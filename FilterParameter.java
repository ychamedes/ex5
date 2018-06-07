package filesprocessing;

/**
 * FilterParameter class that wraps either a number or string value, representing filter parameters.
 */
public class FilterParameter {

    private double doubleParam;
    private String stringParam;

    /**
     * FilterParameter constructor that wraps a number.
     * @param doubleVar the number parameter.
     */
    public FilterParameter(double doubleVar){
        doubleParam = doubleVar;
    }

    /**
     * FilterParameter constructor that wraps a string.
     * @param stringVar the string parameter.
     */
    public FilterParameter(String stringVar){
        stringParam = stringVar;
    }

    /**
     * Returns a number parameter.
     * @return the parameter.
     */
    double getDoubleParam() {
        return doubleParam;
    }

    /**
     * Returns a string parameter.
     * @return the parameter.
     */
    String getStringParam(){
        return stringParam;
    }
}