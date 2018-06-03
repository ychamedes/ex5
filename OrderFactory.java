/**
 * An OrderFactory that creates an instance of the desired order class.
 */
public class OrderFactory {

    private final String ABS_ORDER = "abs";
    private final String TYPE_ORDER = "type";
    private final String SIZE_ORDER = "size";

    /**
     * Returns the desired type of order, either a regular one or reversed.
     * @param orderType type of order to be returned.
     * @param isNotReversed should the order be reversed or not.
     * @return instance of the desired order.
     */
    public Order getOrder(String orderType, boolean isNotReversed) {

        if (orderType == null) return null;

        if (isNotReversed){
            switch (orderType) {
                case (ABS_ORDER):
                    return new AbsOrder();
                case (TYPE_ORDER):
                    return new TypeOrder();
                case (SIZE_ORDER):
                    return new SizeOrder();
            }
        } else {
            /* Use ReverseOrder decorator. */
            switch (orderType){
                case (ABS_ORDER):
                    return new ReverseOrder(new AbsOrder());
                case (TYPE_ORDER):
                    return new ReverseOrder(new TypeOrder());
                case (SIZE_ORDER):
                    return new ReverseOrder(new SizeOrder());
            }
        }

        return null;
    }
}
