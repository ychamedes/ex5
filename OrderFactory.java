package filesprocessing;

/**
 * An OrderFactory that creates an instance of the desired order class.
 */
public class OrderFactory {

    private static final String ABS_ORDER = "abs";
    private static final String TYPE_ORDER = "type";
    private static final String SIZE_ORDER = "size";

    /**
     * Returns the desired type of order, either a regular one or reversed.
     * @param orderType type of order to be returned.
     * @param isReversed should the order be reversed or not.
     * @return instance of the desired order.
     */
    public static Order getOrder(String orderType, boolean isReversed) throws TypeIErrorException {

        if (!isReversed){
            switch (orderType) {
                case ABS_ORDER:
                    return new AbsOrder();
                case TYPE_ORDER:
                    return new TypeOrder();
                case SIZE_ORDER:
                    return new SizeOrder();
                default:
                    throw new TypeIErrorException();
            }
        } else {
            /* Use ReverseOrder decorator. */
            switch (orderType){
                case ABS_ORDER:
                    return new ReverseOrder(new AbsOrder());
                case TYPE_ORDER:
                    return new ReverseOrder(new TypeOrder());
                case SIZE_ORDER:
                    return new ReverseOrder(new SizeOrder());
                default:
                    throw new TypeIErrorException();
            }
        }
    }
}