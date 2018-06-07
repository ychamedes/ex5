package filesprocessing;

/**
 * TypeIIErrorException class, which is called when there is a Type II Error.
 */
public class TypeIIErrorException extends Exception {

    /**
     * TypeIIErrorException Constructor, which receives a message to display.
     * @param errorMessage the message to display.
     */
    public TypeIIErrorException (String errorMessage){
        super(errorMessage);
    }
}
