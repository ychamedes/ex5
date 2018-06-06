package filesprocessing;

public class FilterParameter {

    private double doubleParam;
    private String stringParam;

    FilterParameter(double doubleVar){
        doubleParam = doubleVar;
    }

    FilterParameter(String stringVar){
        stringParam = stringVar;
    }

    double getDoubleParam() {
        return doubleParam;
    }

    String getStringParam(){
        return stringParam;
    }
}