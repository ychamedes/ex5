public class FilterParameter {

    private int intParam;
    private String stringParam;

    FilterParameter(int intVar){
        intParam = intVar;
    }

    FilterParameter(String stringVar){
        stringParam = stringVar;
    }

    int getIntParam() {
        return intParam;
    }

    String getStringParam(){
        return stringParam;
    }
}
